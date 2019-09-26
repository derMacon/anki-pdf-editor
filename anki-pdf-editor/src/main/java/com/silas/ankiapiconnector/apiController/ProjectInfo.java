package com.silas.ankiapiconnector.apiController;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {


    private static final String VIM_USAGE =
                      "************************************************\n"
                    + "*         Anki-Editor - version 1.0            *\n"
                    + "*              In normal mode:                 *\n"
                    + "*  - type ,c to add a template for a new card  *\n"
                    + "*  - type ,p to paste the current page number  *\n"
                    + "************************************************\n\n\n";


    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
    private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");
    private static final String DECK_DIR = LAST_DOCS_DIR + "decks/%s.anki";

    private String deck;
    private String pdf;
    private int currPageNum = 1;

    public ProjectInfo(String deck, String pdfName) {
        this.pdf = LAST_DOCS_DIR + pdfName;
        this.deck = String.format(DECK_DIR, deck);
        updateDeckFile();
    }

    public ProjectInfo() throws IOException {
        String deck = null;
        String pdf = null;
        int n_lines = 2;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);

        // parse .projectHistory file
        while (counter < n_lines) {
            String line = object.readLine();
            if (line.startsWith("deck")) {
                deck = line.split(":")[1];
            } else if (line.startsWith("pdf")) {
                pdf = LAST_DOCS_DIR + line.split(":")[1];
            } else {
                throw new IOException("input line does not match pattern");
            }
            counter++;
        }

        this.pdf = pdf;
        this.deck = String.format(DECK_DIR, deck);
        updateDeckFile();
    }

    private void updateDeckFile() {
        File file = new File(deck);
        if (!file.exists() && !file.isDirectory()) {
            try {
                FileUtils.writeStringToFile(file, VIM_USAGE);
                System.out.println("file wrote");
                System.out.println(file);
            } catch (IOException e) {
                System.out.println("Cannot write file " + file + "\n" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String getDeck() {
        return deck;
    }

    public String getPdfPath() {
        return pdf;
    }

    public String getPdfName() {
        return FilenameUtils.removeExtension(new File(pdf).getName());
    }

    public String getLastDocsDir() {
        return LAST_DOCS_DIR;
    }

    public int getCurrPageNum() {
        return currPageNum;
    }

    public void updatePage(int currPage) {
        this.currPageNum = currPage;
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
                + "page" + currPageNum + "\n";
    }

}
