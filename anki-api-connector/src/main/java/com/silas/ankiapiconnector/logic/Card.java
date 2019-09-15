package com.silas.ankiapiconnector.logic;

import java.util.Arrays;

public class Card {
    private String deckName;
    private String modelName;
    private Fields fields;
    private Options options;
    private String[] tags;
    private Object audio;

    public Card(String deckName, String modelName, String front, String back, String[] tags) {
        this.deckName = deckName;
        this.modelName = modelName;
        this.fields = new Fields(front, back);
        this.options = new Options();
        this.tags = tags;
        this.audio = new Object();
    }

    public String getDeckName() {
        return deckName;
    }

    public String getModelName() {
        return modelName;
    }

    public Fields getFields() {
        return fields;
    }

    public Options getOptions() {
        return options;
    }

    public String[] getTags() {
        return tags;
    }

    public Object getAudio() {
        return audio;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setAudio(Object audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "front: " + this.fields.getFront() + "\n"
                + "back: " + this.fields.getBack() + "\n"
                + "tags: " + Arrays.toString(this.tags);
    }
}
