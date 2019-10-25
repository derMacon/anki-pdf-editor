package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

import java.util.ArrayList;
import java.util.Map;

/**
 * Anki card model that will be used by the gson lib to parse the given json
 * output from the anki connect addon / api.
 */
public class AnkiConnectCardModel {
    private Long noteId;
    private ArrayList<String> tags;
    private Map<String, Field> fields;
    private String modelName;
    private ArrayList<Long> cards;

    public AnkiConnectCardModel(Long noteId, ArrayList<String> tags, Map<String, Field> fields, String modelName, ArrayList<Long> cards) {
        this.noteId = noteId;
        this.tags = tags;
        this.fields = fields;
        this.modelName = modelName;
        this.cards = cards;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "noteId: " + this.noteId
                + "; tags: " + this.tags.toString()
                + "; fields: " + this.fields.toString()
                + "; modelName: " + this.modelName
                + "; cards: " + this.cards.toString();
    }

}

