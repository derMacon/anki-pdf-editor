package com.dermacon.ankiRequest.request;

import com.dermacon.ankiRequest.response.GetDecksResponse;

import java.lang.reflect.Type;

public class GetDecksAnkiRequest extends AnkiRequest {

    private static Type RESPONSE_TYPE = GetDecksResponse.class;

    private static String JSON_TEMPLATE = "{\n" +
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
