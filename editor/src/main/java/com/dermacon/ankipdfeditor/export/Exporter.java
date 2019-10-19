package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Exporter {

    private final String rootDir;

    public Exporter(String rootDir) {
        this.rootDir = rootDir;
    }

    public void createOutput(String deckname) throws IOException {
        List<Card> stack = AnkiConnector.getCardsFromDeck(deckname);
        String content = parseFormat(stack);
        writeFile(deckname, content);
    }

    abstract String parseFormat(List<Card> stack);

    private void writeFile(String deckname, String output) throws IOException {
        System.out.println("todo file io:\n" + output);
        System.out.println("path: " + rootDir + deckname + ".html");

        File file = new File(rootDir + deckname + ".html");
        FileUtils.writeStringToFile(file, output, true);
    }
}
