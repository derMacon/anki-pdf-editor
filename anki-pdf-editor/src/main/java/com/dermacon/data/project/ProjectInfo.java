package com.dermacon.data.project;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final String deck;
    private final String pdf;
    private final PageData pages;

    public ProjectInfo(String deck, String pdf, PageData pages) {
        this.deck = deck;
        this.pdf = pdf;
        this.pages = pages;
    }

    public String getDeck() {
        return deck;
    }

    public String getPdf() {
        return pdf;
    }

    public PageData getPages() {
        return pages;
    }


    // todo check if needed
    public String toJson() {
        return String.format(JSON_TEMPLATE, deck, pdf);
    }

    public String toUrlParameters() {
        return String.format(URL_PARAMETER_TEMPLATE, deck, pdf);
    }

    @Override
    public String toString() {
        return "Deck: " + deck + "\n"
                + "pdf: " + pdf + "\n"
                + "page" + pages.toString() + "\n";
    }
}
