package com.dermacon.ankipdfeditor.ankiApi.request;

import com.dermacon.ankipdfeditor.ankiApi.response.IDReply;

import java.lang.reflect.Type;

public class CreateDeckAnkiRequest extends AnkiRequest {

    private static final Type RESPONSE_TYPE = IDReply.class;

    private static final String JSON_TEMPLATE = "{\n"
            + "\"action\": \"createDeck\",\n"
            + "\"version\": %s,\n"
            + "\"params\": {\"deck\": %s}"
            + "}";

    private final String deckName;

    public CreateDeckAnkiRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE,
                this.version,
                "\"" + removeExtension(this.deckName) + "\""
        );
    }

    @Override
    public Type getResponseType() {
        return RESPONSE_TYPE;
    }
}
