package com.silas.ankiapiconnector.logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardFactory {

    /**
     * Syntax:
     * 1. lines beginning with * => comment
     * 2. front: [text]
     * 3. back: [text]
     * 4. tags: [multiple tags]
     * 5. line beginning with - => delimiter
     * @param editorInput
     * @return
     */
    public static List<Card> produceStack(String editorInput) throws  IncompleteCardException {
        editorInput = editorInput.trim();
        String deckname = parseDeckname(editorInput);
        String[] arr = editorInput.split("-+\n");
        List<Card> outputStack = new LinkedList<>();
        for(String cardBlock : arr) {
            outputStack.add(interpretCard(deckname, cardBlock));
        }
        return outputStack;
    }

    private static Card interpretCard(String deckname, String cardBlock) throws IncompleteCardException {
        String front = null;
        String back = null;
        String[] tags = new String[] {};
        for(String line : cardBlock.split(System.getProperty("line.separator"))) {
            if (line.length() > 0 && !line.startsWith("*") && !line.startsWith("=")) {

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
            throw new IncompleteCardException("one of the fields is empty");
        }

        return new Card(deckname, front, back, tags);
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
}
