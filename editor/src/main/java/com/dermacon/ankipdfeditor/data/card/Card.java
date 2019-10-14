package com.dermacon.ankipdfeditor.data.card;

import java.util.Arrays;

public class Card {

    private static String URL_PARAM_TEMPLATE =
            "deckName=%s&" +
            "frontSide=%s&" +
            "backSide=%s&" +
            "tags=%s";

    private String deckName;
    private String frontSide;
    private String backSide;
    private String[] tags;

    public Card(String deckName, String frontSide, String backSide, String[] tags) {
        this.deckName = deckName;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.tags = tags;
    }

    public String getDeckName() {
        return deckName;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public String[] getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "deck: " + this.deckName + "\n"
                + "frontSide: " + this.frontSide + "\n"
                + "backSide: " + this.backSide + "\n"
                + "tags: " + Arrays.toString(this.tags);
    }


    public String toUrlParam() {
        return String.format(URL_PARAM_TEMPLATE,
                deckName,
                frontSide,
                backSide,
                arrayToJson(tags)
        );
    }

    private static String arrayToJson(String[] arr) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            output.append(arr[i]);
            if (i < arr.length - 1) {
                output.append(",");
            }
        }
        return output.toString();

        // not working: this output = ["data1", "data2"]
        // desired: output = [data1, data2]
//        JSONArray output = new JSONArray();
//        for (Object curr : arr) {
//            output.put(curr);
//        }
//        return output.toString();
    }
}
