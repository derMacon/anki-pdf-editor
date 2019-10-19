package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import com.dermacon.ankipdfeditor.data.project.ProjectInfo;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Exporter {

    private final ProjectInfo projectInfo;
    protected List<Card> stack;

    public Exporter(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public void createOutput() throws IOException {
        String deckname = removeExtension(projectInfo.getDeckFile().getName());
        stack = AnkiConnector.getCardsFromDeck(deckname);
        copyImages();
        String content = parseFormat();
        writeFile(deckname, content);
    }

    abstract String parseFormat();

    private void writeFile(String deckname, String output) throws IOException {
        System.out.println("todo file io:\n" + output);
        System.out.println("path: " + projectInfo.getExportDir() + deckname + ".html");

        File file = new File(projectInfo.getExportDir() + deckname + ".html");
        FileUtils.writeStringToFile(file, output, true);
    }

    private void copyImages() {
        List<String> foundImages = new LinkedList<>();

        for (Card card : stack) {
            foundImages.addAll(extractImg(card.getFrontSide()));
            foundImages.addAll(extractImg(card.getBackSide()));
        }

        for (String img : foundImages) {
            copyImg(img);
        }
    }

    private List<String> extractImg(String sideContent) {
        // todo
        System.out.println("extract img - WIP - Exporter.java");
        return null;
    }

    private void copyImg(String imgName) {
        // todo
        System.out.println("copy img: " + imgName);
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
