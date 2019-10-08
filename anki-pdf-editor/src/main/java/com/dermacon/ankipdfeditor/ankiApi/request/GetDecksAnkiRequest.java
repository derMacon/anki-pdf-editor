package com.dermacon.ankipdfeditor.ankiApi.request;


import com.dermacon.ankipdfeditor.ankiApi.response.GetDecksResponse;

import java.lang.reflect.Type;

public class GetDecksAnkiRequest extends AnkiRequest {

    private static final Type RESPONSE_TYPE = GetDecksResponse.class;

    private static final String JSON_TEMPLATE = "{\n" +
            "\"action\": \"deckNames\",\n" +
            "\"version\": %s\n" +
            "}";

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE, this.version);
    }

    @Override
    public Type getResponseType() {
        return RESPONSE_TYPE;
    }
}
