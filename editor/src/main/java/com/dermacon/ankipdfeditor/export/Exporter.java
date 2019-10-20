package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Exporter {

    protected final ExportInfo exportInfo;

    public Exporter(ExportInfo exportInfo) {
        this.exportInfo = exportInfo;
    }

    public void createOutput() throws IOException {
        String deckname = removeExtension(exportInfo.getDeckname());
        List<Card> stack = AnkiConnector.getCardsFromDeck(deckname);
        String content = parseFormat(stack);
        writeFile(deckname, content);
    }

    abstract String parseFormat(List<Card> stack);

    private void writeFile(String deckname, String output) throws IOException {
        System.out.println("todo file io:\n" + output);
        System.out.println("path: " + exportInfo.getExportPath() + deckname + ".html");

        File file = new File(exportInfo.getExportPath() + deckname + ".html");
        FileUtils.writeStringToFile(file, output, true);
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
