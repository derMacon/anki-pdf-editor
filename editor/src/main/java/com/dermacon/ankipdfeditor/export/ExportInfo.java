package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.IncompleteSyntaxException;

import java.io.File;

public class ExportInfo {

    private final String deckname;
    private final String mediaPath;
    private final String exportDocPath;
    private final String exportImgPath;
    private final Formating formating;

    public static class ExportInfoBuilder {

        private String deckname;
        private String mediaPath;
        private String exportDocPath;
        private String exportImgPath;
        private Formating formating;

        public ExportInfoBuilder setDeckname(String deckname) {
            this.deckname = deckname;
            return this;
        }

        public ExportInfoBuilder setMediaPath(String mediaPath) {
            this.mediaPath = mediaPath;
            return this;
        }

        public ExportInfoBuilder setExportRoot(String exportRoot) {
            exportDocPath = exportRoot + deckname + File.separator;
            exportImgPath = exportDocPath + "img" + File.separator;
            saveMkDir(exportDocPath);
            saveMkDir(exportImgPath);
            return this;
        }

        public ExportInfoBuilder setFormating(Formating formating) {
            this.formating = formating;
            return this;
        }

        public ExportInfo build() throws IncompleteSyntaxException {
            return new ExportInfo(this);
        }

        /**
         * Creates the directory at the specified directory if it's not already created
         * @param path path to the directory that will be created.
         */
        private void saveMkDir(String path) {
            File dir = new File(path);
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdir();
            }
        }

    }

    private ExportInfo(ExportInfoBuilder builder) {
        this.deckname = builder.deckname;
        this.mediaPath = builder.mediaPath;
        this.exportDocPath = builder.exportDocPath;
        this.exportImgPath = builder.exportImgPath;
        this.formating = builder.formating;

        if (deckname == null || mediaPath == null || exportDocPath == null
                || formating == null || exportImgPath == null) {
            throw new IncompleteSyntaxException("ExportInfo - one of the fields was not initialized");
        }
    }

    public String getDeckname() {
        return deckname;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public String getExportDocPath() {
        return exportDocPath;
    }

    public String getExportImgPath() {
        return exportImgPath;
    }

    public Formating getFormating() {
        return formating;
    }
}
