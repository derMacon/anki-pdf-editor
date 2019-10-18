package com.dermacon.ankipdfeditor.ankiApi.request.function;

public class FindNotesRequest extends FunctionalRequest {

    private static final String JSON_TEMPLATE = "{\n"
            + "\"action\": \"findNotes\",\n"
            + "\"version\": %s,\n"
            + "\"params\": {\"query\": \"deck:%s\"}"
            + "}";

    private final String deckName;

    public FindNotesRequest(String deckName) {
        super();
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
