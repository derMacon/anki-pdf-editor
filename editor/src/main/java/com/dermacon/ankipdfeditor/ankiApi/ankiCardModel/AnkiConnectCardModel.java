package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

import java.util.ArrayList;
import java.util.Map;

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

    @Override
    public String toString() {
        return "noteId: " + this.noteId
                + "; tags: " + this.tags.toString()
                + "; fields: " + this.fields.toString()
                + "; modelName: " + this.modelName
                + "; cards: " + this.cards.toString();
    }

}

