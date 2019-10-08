package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.data.project.ProjectInfo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardStackFactory {

    public static List<Card> produceStack(ProjectInfo projectInfo) throws IOException, IncompleteCardException {
        File deck = projectInfo.getDeck();
        String fileContent = FileUtils.readFileToString(deck, "UTF-8");
        return produceStack(fileContent);
    }

    /**
     * Syntax:
     * 1. comment: lines beginning with *
     * 2. front: [text]
     * 3. back: [text]
     * 4. tags: [multiple tags]
     * 5. delimiter: line beginning with -
     * @param editorOutput
     * @return
     */
    public static List<Card> produceStack(String editorOutput) throws IncompleteCardException {
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

    private static Card interpretCard(String deckname, String cardBlock) throws IncompleteCardException {
        String front = null;
        String back = null;
        String[] tags = new String[] {};
        for(String line : cardBlock.split(System.getProperty("line.separator"))) {
            if (line.length() > 0 && !line.startsWith("*") && !line.startsWith("-")) {

                 if (line.startsWith("front:")) {
                    front = line.replace("front: ", "");
                 } else if (line.startsWith("back:")) {
                    back = line.replace("back: ", "");
                } else if (line.startsWith("tags:")) {
                    line = line.replace("tags: ", "");
                    tags = line.split(" ");
                }

            }
        }

        if (front == null || deckname == null || front == null || back == null) {
            throw new IncompleteCardException("one of the fields is empty:\n" + cardBlock);
        }

        return new Card(deckname, front, back, tags);
    }

}
