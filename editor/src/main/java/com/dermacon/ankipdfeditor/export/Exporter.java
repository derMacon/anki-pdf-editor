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

    /**
     * Writes the given content to a file with the name of the deck and the given format.
     * Important: already existent files will be overwritten.
     * @param deckname
     * @param content
     * @throws IOException
     */
    private void writeFile(String deckname, String content) throws IOException {
        System.out.println("path: " + exportInfo.getExportPath() + deckname + ".html");
        File file = new File(exportInfo.getExportPath() + deckname + ".html");
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
