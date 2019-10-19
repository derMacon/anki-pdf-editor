package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.data.project.ProjectInfo;
import com.dermacon.ankipdfeditor.data.worker.parser.HtmlCardParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates a stack of notes / cards based on a given projectinfo component.
 */
public class CardStackFileFactory {

    private ProjectInfo projectInfo;
    private HtmlCardParser imgParser;

    public CardStackFileFactory(ProjectInfo projectInfo) throws IOException {
        this.projectInfo = projectInfo;
        this.imgParser = new HtmlCardParser(projectInfo.getPdf());
    }

    /**
     * Syntax:
     * 1. comment: lines beginning with *
     * 2. front: [text]
     * 3. back: [text]
     * 4. tags: [multiple tags]
     * 5. delimiter: line beginning with -
     * @return
     */
    public List<Card> produceStack() throws IncompleteSyntaxException, IOException {
        File deck = projectInfo.getDeckFile();
        String editorOutput = FileUtils.readFileToString(deck, "UTF-8");

        editorOutput = editorOutput.trim();
        String deckname = parseDeckname(editorOutput);
        String[] arr = editorOutput.split("\n-+\n");
        List<Card> outputStack = new LinkedList<>();
        for(String cardBlock : arr) {
            outputStack.add(interpretCard(deckname, cardBlock));
        }
        return outputStack;
    }

    private static String parseDeckname(String editorInput) {
        String deckName = "error";
        Pattern pattern = Pattern.compile("'(.*?)'");
        Matcher matcher = pattern.matcher(editorInput);
        if (matcher.find()) {
            deckName = matcher.group(1);
        }
        return deckName;
    }

    /**
     * Generates a single card form a given deck name and the
     * previously generated card block.
     * @param deckname deck name of the corresponding deck
     * @param cardBlock card block containing the card data in typical
     *                  property notation "front:(.*)back:(.*)tags:(.*)"
     * @return card containing the given information
     * @throws IncompleteSyntaxException thrown when the cardblock has an incorrect syntax
     * @throws IOException thrown when the image render process is not working as intended
     */
    public Card interpretCard(String deckname, String cardBlock) throws IncompleteSyntaxException, IOException {
        // remove delimiter
        cardBlock = cardBlock.replaceAll("\n-+$", "");

        Pattern pattern = Pattern.compile("front:(.*)back:(.*)tags:(.*)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(cardBlock);

        if (!matcher.find()) {
            throw new IncompleteSyntaxException("one of the fields is empty:\n" + cardBlock);
        }

        return new Card(deckname,
                imgParser.parseHtml(matcher.group(1).trim()),
                imgParser.parseHtml(matcher.group(2).trim()),
                matcher.group(3).trim().split(" ")
        );
    }

}
