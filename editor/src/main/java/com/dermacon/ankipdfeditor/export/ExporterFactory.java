package com.dermacon.ankipdfeditor.export;

import java.io.File;

public class ExporterFactory {

    public static Exporter createExporter(String exportDir, Formating formating) {
        Exporter output = null;
        switch (formating) {
            case HTML:
                output = new HtmlExporter(exportDir);
                break;
            case PDF:
                // todo
                break;
        }
        return output;
    }

}
