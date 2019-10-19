package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

import java.util.ArrayList;
import java.util.Map;

public class AnkiConnectCardModel {
    private Long noteId;
    private ArrayList<String> tags;
//    private Fields fields;
//    private String modelName;

//    public AnkiConnectCardModel(Long noteId, String modelName, ArrayList<String> tags, Map<String, Field> fields) {
//        this.noteId = noteId;
//        this.modelName = modelName;
//        this.tags = tags;
//        this.fields = fields;
//    }


    public AnkiConnectCardModel(Long noteId, ArrayList<String> tags) {
        this.noteId = noteId;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "noteId: " + this.noteId
                + "tags: " + this.tags.toString();
    }

}

