package com.dermacon.ankipdfeditor.export;

import com.dermacon.ankipdfeditor.data.card.IncompleteSyntaxException;

public class ExportInfo {

    private final String deckname;
    private final String mediaPath;
    private final String exportPath;
    private final Formating formating;

    public static class ExportInfoBuilder {

        private String deckname;
        private String mediaPath;
        private String exportPath;
        private Formating formating;

        public ExportInfoBuilder setDeckname(String deckname) {
            this.deckname = deckname;
            return this;
        }

        public ExportInfoBuilder setMediaPath(String mediaPath) {
            this.mediaPath = mediaPath;
            return this;
        }

        public ExportInfoBuilder setExportPath(String exportPath) {
            this.exportPath = exportPath;
            return this;
        }

        public ExportInfoBuilder setFormating(Formating formating) {
            this.formating = formating;
            return this;
        }

        public ExportInfo build() throws IncompleteSyntaxException {
            return new ExportInfo(this);
        }

    }

    private ExportInfo(ExportInfoBuilder builder) {
        this.deckname = builder.deckname;
        this.mediaPath = builder.mediaPath;
        this.exportPath = builder.exportPath;
        this.formating = builder.formating;

        if (deckname == null || mediaPath == null || exportPath == null || formating == null) {
            throw new IncompleteSyntaxException("ExportInfo - one of the fields was not initialized");
        }
    }

    public String getDeckname() {
        return deckname;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public String getExportPath() {
        return exportPath;
    }

    public Formating getFormating() {
        return formating;
    }
}
