package com.silas.ankiapiconnector.ankiRequest.request;

import com.silas.ankiapiconnector.ankiRequest.response.AddNoteResponse;
import com.silas.ankiapiconnector.logic.Card;

import java.lang.reflect.Type;
import java.util.Arrays;

public class AddNoteRequest extends Request {

    private static Type RESPONSE_TYPE = AddNoteResponse.class;

    // template has placeholders for:
    // version, deckName, Front, Back, tags
    private static String JSON_TEMPLATE = "{\n" +
            "\"action\": \"addNotes\",\n" +
            "\"version\": %s,\n" +
            "\"params\": {\n" +
            "  \"notes\": [ \n" +
            "    {\n" +
            "      \"deckName\": \"%s\",\n" +
            "      \"modelName\": \"Basic\",\n" +
            "      \"fields\": {\n" +
            "        \"Front\": \"%s\",\n" +
            "        \"Back\": \"%s\"\n" +
            "      },\n" +
            "      \"options\": {\n" +
            "        \"allowDuplicate\": true\n" +
            "      },\n" +
            "      \"tags\": %s\n" +
            "    } \n" +
            "  ]\n" +
            "}\n" +
            "}";

    private Card card;

    public AddNoteRequest(Card card) {
        this.card = card;
    }

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE,
                String.valueOf(this.version),
                card.getDeckName(),
                card.getFrontSide(),
                card.getBackSide(),
                Arrays.toString(card.getTags())
        );
    }

    @Override
    public Type getResponseType() {
        return RESPONSE_TYPE;
    }
}
