package com.dermacon.data.project;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final String deckPath;
    private int currPage;

    private final PDDocument pdfDoc;
    private final String pdfPath;
    private final String imgPath;
    private final String projHistoryPath;


    /**
     * Package private constructor, (in theory) only the InfoBuilder can access it.
     * @param deckPath
     * @param pdfPath
     * @param currPage
     * @throws IOException
     */
    ProjectInfo(String deckPath, String pdfPath, String imgPath, String projHistoryPath, int currPage) throws IOException {
        this.deckPath = deckPath;
        this.pdfDoc = PDDocument.load(new File(pdfPath));
        this.pdfPath = pdfPath;
        this.imgPath = imgPath;
        this.projHistoryPath = projHistoryPath;
        this.currPage = currPage;
    }

    public String getDeckPath() {
        return deckPath;
    }

    public String getDeckName() {
        return extractFileName(deckPath);
    }

    private String extractFileName(String path) {
        return FilenameUtils.removeExtension(FilenameUtils.getName(deckPath));
    }

    public PDDocument getPdfDoc() {
        return pdfDoc;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public String getPdfName() {
        return extractFileName(pdfPath);
    }

    public int getCurrPage() {
        return currPage;
    }

    public String getImgPath(int pageNum) {
        return imgPath + getPdfName() + "_" + pageNum + ".png";
    }

    public String getCurrImgPath() {
        return getImgPath(currPage);
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
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
        return "deck:" + getDeckName() + "\n"
                + "pdf:" + getPdfName() + "\n"
                + "page:" + currPage + "\n\n";
    }

    public void saveToFile() throws IOException {
        FileUtils.writeStringToFile(new File(projHistoryPath), toString());
    }

}
