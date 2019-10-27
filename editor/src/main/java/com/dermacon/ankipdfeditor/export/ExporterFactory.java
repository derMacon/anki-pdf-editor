package com.dermacon.ankipdfeditor.export;

public class ExporterFactory {

    public static Exporter createExporter(ExportInfo info) {
        Exporter output = null;
        switch (info.getFormating()) {
            case HTML:
                output = new HtmlExporter(info);
                break;
            case TEX:
                output = new TexExporter(info);
                break;
        }
        return output;
    }

}
