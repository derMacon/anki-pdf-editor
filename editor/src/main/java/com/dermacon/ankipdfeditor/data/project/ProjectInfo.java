package com.dermacon.ankipdfeditor.data.project;

import javafx.scene.image.Image;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * Main component holding all relevant paths of the file io and manages the
 * current selected project / stack which the user is working on.
 */
public class ProjectInfo {

    private final static String BORDER_TOKEN = "*";

    // todo delete.
    private final static String JSON_TEMPLATE = "{\n" +
            "\"deck\": \"%s\",\n" +
            "\"pdf\": \"%s\"\n" +
            "}";

    // todo delete.
    private final static String URL_PARAMETER_TEMPLATE = "deck=%s&pdf=%s";

    // .vimrc that will be loaded for the costum session
    private final File sessionVimrc;
    // file that holds the information with the last selected project.
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

    /**
     * Getter for the deck file
     * @return deckfile
     */
    public File getDeckFile() {
        return deck;
    }

    /**
     * Getter for the deckname
     * @return deckname
     */
    public String getDeckName() {
        return removeExtension(deck.getName());
    }

    /**
     * Removes the extension from a given full qualified file.
     * @param fullFileName full qualified file name
     * @return name without extension
     */
    protected static String removeExtension(String fullFileName) {
        int idx = fullFileName.lastIndexOf('.');
        if (idx > 0) {
            fullFileName = fullFileName.substring(0, idx);
        }
        return fullFileName;
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
        return imgPath + removeExtension(pdf.getName()) + "_" + pageNum + ".png";
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

    public void setExportPath(String exportPath) {
        this.exportPath = exportPath;
    }


    @Override
    public String toString() {
        return "deck: " + deck.getName() + "\n"
                + "pdf:  " + pdf.getName() + "\n"
                + "page: " + currPage + "\n"
                + "exp:  " + exportPath + "\n";
    }

    /**
     * Generates a formatted string that will be displayed on the terminal gui.
     * @return formatted string
     */
    public String toFormattedString() {
        String deckStr = "deck: " + deck.getName();
        String pdfStr = "pdf:  " + pdf.getName();
        String pageStr = "page: " + currPage;
        String expStr = "export directory: " + exportPath + "\n";

        int lineLength = Integer.max(deckStr.length(), pdfStr.length()) + 4;
        return generateLine("", lineLength)
                + generateLine(deckStr, lineLength)
                + generateLine(pdfStr, lineLength)
                + generateLine(pageStr, lineLength)
                + generateLine("", lineLength)
                + expStr;
    }

    /**
     * Surrounds a given line with a border
     * @param originalLine line that will be edited
     * @param lineLength length of the output
     * @return formatted String
     */
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

    /**
     * Saves its state to the specified .projectHistory file
     * @throws IOException thrown if there was an error with the file io
     */
    public void saveToFile() throws IOException {
        FileUtils.writeStringToFile(projHistory, toString());
    }

}
