package com.dermacon.ankipdfeditor.ankiApi.request.consumer;

import com.dermacon.ankipdfeditor.ankiApi.request.AnkiRequest;

public class CreateDeckAnkiRequest extends ConsumingRequest {

    private static final String JSON_TEMPLATE = "{\n"
            + "\"action\": \"createDeck\",\n"
            + "\"version\": %s,\n"
            + "\"params\": {\"deck\": \"%s\"}"
            + "}";

    private final String deckName;

    public CreateDeckAnkiRequest(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE,
                this.version,
                removeExtension(this.deckName)
        );
    }

}
