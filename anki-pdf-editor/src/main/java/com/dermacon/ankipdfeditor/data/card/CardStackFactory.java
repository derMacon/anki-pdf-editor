package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.data.project.ProjectInfo;
import com.dermacon.ankipdfeditor.data.worker.parser.HtmlParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardStackFactory {

    private ProjectInfo projectInfo;
    private HtmlParser imgParser;

    public CardStackFactory(ProjectInfo projectInfo) throws IOException {
        this.projectInfo = projectInfo;
        this.imgParser = new HtmlParser(projectInfo.getPdf());
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
    public List<Card> produceStack() throws IncompleteCardException, IOException {
        File deck = projectInfo.getDeck();
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

    public Card interpretCard(String deckname, String cardBlock) throws IncompleteCardException, IOException {
        Pattern pattern = Pattern.compile("front:(.*)back:(.*)tags:(.*)\n-", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(cardBlock);

        if (!matcher.find()) {
            throw new IncompleteCardException("one of the fields is empty:\n" + cardBlock);
        }

        return new Card(deckname,
                imgParser.parseHtml(matcher.group(1).trim()),
                imgParser.parseHtml(matcher.group(2).trim()),
                matcher.group(3).trim().split(" ")
        );
    }

//    public static Card interpretCard(String deckname, String cardBlock) throws IncompleteCardException {
//        String front = null;
//        String back = null;
//        String[] tags = new String[] {};
//        for(String line : cardBlock.split("\\n *\\n")) {
//            if (line.length() > 0 && !line.startsWith("*") && !line.startsWith("-")) {
//
//                 if (line.startsWith("front:")) {
//                    front = line.replace("front: ", "");
//                 } else if (line.startsWith("back:")) {
//                    back = line.replace("back: ", "");
//                } else if (line.startsWith("tags:")) {
//                    line = line.replace("tags: ", "");
//                    tags = line.split(" ");
//                }
//
//            }
//        }
//
//        if (front == null || deckname == null || front == null || back == null) {
//            throw new IncompleteCardException("one of the fields is empty:\n" + cardBlock);
//        }
//
//        return new Card(deckname, front, back, tags);
//    }

}
