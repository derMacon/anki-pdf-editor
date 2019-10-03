package com.dermacon.data.project;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final String deck;
    private final PDDocument pdf;
    private final int currPage;

    public ProjectInfo(String deck, PDDocument pdf, int currPage) throws IOException {
        this.deck = deck;
        this.pdf = pdf;
        this.currPage = currPage;
    }

    public String getDeck() {
        return deck;
    }

    public PDDocument getPdf() {
        return pdf;
    }

    public int getCurrPage() {
        return currPage;
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
                + "currPage: " + currPage + "\n"
                + "pageCnt: " + pdf.getNumberOfPages() + "\n";
    }
}
