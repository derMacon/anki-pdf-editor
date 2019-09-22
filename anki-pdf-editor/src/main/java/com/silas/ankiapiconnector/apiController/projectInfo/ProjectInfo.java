package com.silas.ankiapiconnector.apiController.projectInfo;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
    private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");

    private String deck;
    private String pdf;

    ProjectInfo(String deck, String pdfName) {
        this.deck = deck;
        this.pdf = LAST_DOCS_DIR + pdfName;
    }

    ProjectInfo() throws IOException {
        int n_lines = 2;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);
        while(counter < n_lines) {
            String line = object.readLine();
            if (line.startsWith("deck")) {
                this.deck = line.split(":")[1];
            } else if (line.startsWith("pdf")) {
                this.pdf = LAST_DOCS_DIR + line.split(":")[1];
            } else {
                throw new IOException("input line does not match pattern");
            }
            counter++;
        }
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public static File getProjHistory() {
        return PROJ_HISTORY;
    }

    public String getDeck() {
        return deck;
    }

    public String getPdf() {
        return pdf;
    }

    public String toJson() {
        return String.format(JSON_TEMPLATE, deck, pdf);
    }

    public String toUrlParameters() {
        return String.format(URL_PARAMETER_TEMPLATE, deck, pdf);
    }

}
