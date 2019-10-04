package com.dermacon.data.project;

import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final File projHistory;
    private final File deck;
    private final File pdf;
    private final PDDocument pdfPDDoc;
    private final String imgPath;
    private int currPage;


    /**
     * Package private constructor, (in theory) only the InfoBuilder can access it.
     * @param deck
     * @param pdf
     * @param projHistory
     * @param imgPath
     * @param currPage
     * @throws IOException
     */
    ProjectInfo(File deck, File pdf, File projHistory, String imgPath, int currPage) throws IOException {
        this.deck = deck;
        this.pdf = pdf;
        this.pdfPDDoc = PDDocument.load(new File(pdf.getPath()));
        this.projHistory = projHistory;
        this.imgPath = imgPath;
        this.currPage = currPage;
    }

    public File getDeck() {
        return deck;
    }

    public PDDocument getPdfPDDoc() {
        return pdfPDDoc;
    }

    public int getCurrPage() {
        return currPage;
    }

    public String getImgPath(int pageNum) {
        return imgPath + pdf.getName() + "_" + pageNum + ".png";
    }

    public Image getCurrImg() {
        return new Image("file:" + getImgPath(currPage));
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }


    // todo check if needed
//    public String toJson() {
//        return String.format(JSON_TEMPLATE, deckPath, pdfPDDoc);
//    }
//
//    public String toUrlParameters() {
//        return String.format(URL_PARAMETER_TEMPLATE, deckPath, pdfPDDoc);
//    }

    @Override
    public String toString() {
        return "deck:" + deck.getName() + "\n"
                + "pdf:" + pdf.getName() + "\n"
                + "page:" + currPage + "\n\n";
    }

    public void saveToFile() throws IOException {
        FileUtils.writeStringToFile(projHistory, toString());
    }

}
