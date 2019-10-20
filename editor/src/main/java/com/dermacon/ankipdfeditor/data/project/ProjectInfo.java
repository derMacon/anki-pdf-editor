package com.dermacon.ankipdfeditor.data.project;

import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class ProjectInfo {

    private final static String BORDER_TOKEN = "*";

    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    private final File sessionVimrc;
    private final File projHistory;

    private File deck;
    private File pdf;
    private PDDocument pdfPDDoc;
    private String imgPath;
    private String exportPath;
    private String mediaDir;
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
    ProjectInfo(File deck, File pdf, File projHistory, File sessionVimrc, String imgPath, String exportPath, String mediaDir, int currPage) throws IOException {
        this.deck = deck;
        this.pdf = pdf;
        this.pdfPDDoc = PDDocument.load(new File(pdf.getPath()));
        this.projHistory = projHistory;
        this.sessionVimrc = sessionVimrc;
        this.imgPath = imgPath;
        this.exportPath = exportPath;
        this.mediaDir = mediaDir;
        this.currPage = currPage;
    }

    public File getDeckFile() {
        return deck;
    }

    public String getExportDir() {
        return exportPath;
    }

    public String getMediaDir() {
        return mediaDir;
    }

    public File getPdf() {
        return pdf;
    }

    public File getSessionVimrc() {
        return sessionVimrc;
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

    public void setPdf(File pdf) throws IOException {
        this.pdf = pdf;
        this.pdfPDDoc = PDDocument.load(pdf);
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
        return "deck: " + deck.getName() + "\n"
                + "pdf:  " + pdf.getName() + "\n"
                + "page: " + currPage + "\n";
    }

    public String toFormattedString() {
        String deckStr = "deck: " + deck.getName();
        String pdfStr = "pdf:  " + pdf.getName();
        String pageStr = "page: " + currPage;

        int lineLength = Integer.max(deckStr.length(), pdfStr.length()) + 4;
        return generateLine("", lineLength)
                + generateLine(deckStr, lineLength)
                + generateLine(pdfStr, lineLength)
                + generateLine(pageStr, lineLength)
                + generateLine("", lineLength);
    }

    public static String generateLine(String originalLine, int lineLength) {
        assert (originalLine.length() + 4) <= lineLength;
        if (originalLine.isEmpty()) {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < lineLength; i++) {
                out.append(BORDER_TOKEN);
            }
            return out.toString() + "\n";
        }

        int offset = lineLength - (originalLine.length() + 4);
        for (int i = 0; i < offset; i++) {
            originalLine += " ";
        }

        return BORDER_TOKEN + " " + originalLine + " " + BORDER_TOKEN + "\n";
    }

    public void saveToFile() throws IOException {
        FileUtils.writeStringToFile(projHistory, toString());
    }

}
