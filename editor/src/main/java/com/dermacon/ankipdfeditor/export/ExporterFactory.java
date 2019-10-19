package com.dermacon.ankipdfeditor.export;

import java.io.File;

public class ExporterFactory {

    public static Exporter createExporter(File deckDir, Formating formating) {
        Exporter output = null;
        switch (formating) {
            case HTML:
                output = new HtmlExporter(deckDir);
                break;
            case PDF:
                // todo
                break;
        }
        return output;
    }

}
