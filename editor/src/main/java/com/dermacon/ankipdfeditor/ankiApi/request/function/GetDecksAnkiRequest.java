package com.dermacon.ankipdfeditor.ankiApi.request.function;


import com.dermacon.ankipdfeditor.ankiApi.request.AnkiRequest;

public class GetDecksAnkiRequest extends AnkiRequest {

    private static final String JSON_TEMPLATE = "{\n" +
            "\"action\": \"deckNames\",\n" +
            "\"version\": %s\n" +
            "}";

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE, this.version);
    }

}
