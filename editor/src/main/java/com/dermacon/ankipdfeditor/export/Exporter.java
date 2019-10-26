package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Exporter {

    protected final ExportInfo exportInfo;

    public Exporter(ExportInfo exportInfo) {
        this.exportInfo = exportInfo;
    }

    public void createOutput() throws IOException {
        String deckname = removeExtension(exportInfo.getDeckname());
        List<Card> stack = AnkiConnector.getCardsFromDeck(deckname);
        copyImages(stack);
        String content = parseFormat(stack);
        writeFile(deckname, content);
    }

    /**
     * Copies the images referenced in the cards to the export dir and updates
     * image paths in the cards themselves.
     * @param stack stack of cards to update.
     * @throws IOException thrown if a given image cannot be copied.
     */
    private void copyImages(List<Card> stack) throws IOException {
        Set<String> foundImages = new HashSet<>();

        for (Card card : stack) {
            foundImages.addAll(extractImg(card.getFrontSide()));
            foundImages.addAll(extractImg(card.getBackSide()));
        }

        for (String imgName : foundImages) {
            copyImg(imgName);
        }
    }

    private Set<String> extractImg(String sideContent) {
        System.out.println("extract img - WIP - Exporter.java");
        Pattern p = Pattern.compile("<img src=(.*?)>");
        Matcher m = p.matcher(sideContent);

        Set<String> out = new HashSet<>();
        if (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                out.add(m.group(i).replaceAll("\"", ""));
            }
        }

        return out;
    }

    private void copyImg(String imgName) throws IOException {
        File src = new File(exportInfo.getMediaPath() + File.separator + imgName);
        File tar = new File(exportInfo.getExportImgPath() + imgName);
        System.out.println("copy " + src + " to " + tar);
        FileUtils.copyFile(src, tar);
    }

    abstract String parseFormat(List<Card> stack);

    /**
     * Writes the given content to a file with the name of the deck and the given format.
     * Important: already existent files will be overwritten.
     * @param deckname
     * @param content
     * @throws IOException
     */
    private void writeFile(String deckname, String content) throws IOException {
        System.out.println("path: " + exportInfo.getExportDocPath() + deckname + ".html");
        File file = new File(exportInfo.getExportDocPath() + deckname + ".html");
        if (file.exists() && !file.isDirectory()) {
            System.out.println("deleted file: " + file);
            FileUtils.forceDelete(file);
        }
        FileUtils.writeStringToFile(file, content);
    }

    // todo duplicate from request
    protected static String removeExtension(String fullFileName) {
        int idx = fullFileName.lastIndexOf('.');
        if (idx > 0) {
            fullFileName = fullFileName.substring(0, idx);
        }
        return fullFileName;
    }

}
