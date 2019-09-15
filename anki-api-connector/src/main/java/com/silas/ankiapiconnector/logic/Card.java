package com.silas.ankiapiconnector.logic;

import java.util.Arrays;

public class Card {

    private String deckName;
    private String frontSide;
    private String backSide;
    private String[] tags;

    public Card(String deckName, String frontSide, String backSide, String[] tags) {
        this.deckName = deckName;
        this.frontSide = parseImg(frontSide);
        this.backSide = parseImg(backSide);
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

    public static String parseImg(String side) {
        return side.replaceAll("<(\\d*)>", "<img src=$1.png>");
    }

    @Override
    public String toString() {
        return "front: " + this.frontSide + "\n"
                + "back: " + this.backSide + "\n"
                + "tags: " + Arrays.toString(this.tags);
    }
}
