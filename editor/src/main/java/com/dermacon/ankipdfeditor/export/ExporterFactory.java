package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.project.ProjectInfo;

import java.io.File;

public class ExporterFactory {

    public static Exporter createExporter(ExportInfo info) {
        Exporter output = null;
        switch (info.getFormating()) {
            case HTML:
                output = new HtmlExporter(info);
                break;
            case PDF:
                // todo
                break;
        }
        return output;
    }

}
