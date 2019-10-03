package com.dermacon.data.project;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {

    private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
    private static final String IMG_TEMP_DIR = LAST_DOCS_DIR + "img_temp/";
    private static final String DECK_DIR = LAST_DOCS_DIR + "decks/%s.anki";
    private static final String PDF_DIR = LAST_DOCS_DIR + "pdf/%s";


    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final String deckPath;
    private final int currPage;

    // redundant, but at least encapsulated (file io only in this class)
    // PDDocument not able to return its path
    private final PDDocument pdfDoc;
    private final String pdfPath;


    public static class InfoBuilder {
        private String deckPath, pdfPath;
        private PDDocument pdfDoc;
        private int currPage;

        public void deckName(String deckName) {
            this.deckPath = String.format(DECK_DIR, deckName);
        }

        public void pdfName(String pdfName) {
            this.pdfPath = String.format(PDF_DIR, pdfName);
        }

        public void currPage(String currPage) {
            this.currPage = Integer.parseInt(currPage);
        }

        public ProjectInfo build() throws IOException {
            this.pdfDoc = PDDocument.load(new File(pdfPath));
            return new ProjectInfo(this);
        }
    }

    private ProjectInfo(InfoBuilder builder) {
        this.deckPath = builder.deckPath;
        this.pdfPath = builder.pdfPath;
        this.pdfDoc = builder.pdfDoc;
        this.currPage = builder.currPage;
    }

    public String getDeckPath() {
        return deckPath;
    }

    public PDDocument getPdfDoc() {
        return pdfDoc;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public String getPdfName() {
        return FilenameUtils.removeExtension(FilenameUtils.getName(pdfPath));
    }

    public int getCurrPage() {
        return currPage;
    }

    // todo check if needed
    public String toJson() {
        return String.format(JSON_TEMPLATE, deckPath, pdfDoc);
    }

    public String toUrlParameters() {
        return String.format(URL_PARAMETER_TEMPLATE, deckPath, pdfDoc);
    }

    @Override
    public String toString() {
        return "Deck: " + deckPath + "\n"
                + "pdf: " + pdfDoc + "\n"
                + "currPage: " + currPage + "\n"
                + "pageCnt: " + pdfDoc.getNumberOfPages() + "\n";
    }
}
