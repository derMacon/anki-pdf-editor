package com.silas.ankiapiconnector.apiController;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private String deck;
    private String pdf;

    public ProjectInfo(String deck, String pdf) {
        this.deck = deck;
        this.pdf = pdf;
    }

    public ProjectInfo() {
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String toJson() {
        return String.format(JSON_TEMPLATE, deck, pdf);
    }

}
