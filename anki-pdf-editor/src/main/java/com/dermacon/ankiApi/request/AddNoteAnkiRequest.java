package com.dermacon.ankiApi.request;

import com.dermacon.ankiApi.response.AddNoteResponse;
import com.dermacon.data.card.Card;
import org.json.JSONArray;

import java.lang.reflect.Type;

public class AddNoteAnkiRequest extends AnkiRequest {

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
            "        \"allowDuplicate\": false\n" +
            "      },\n" +
            "      \"tags\": %s\n" +
            "    } \n" +
            "  ]\n" +
            "}\n" +
            "}";

    private Card card;

    public AddNoteAnkiRequest(Card card) {
        this.card = card;
    }

    @Override
    public Type getResponseType() {
        return RESPONSE_TYPE;
    }

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE,
                String.valueOf(this.version),
                card.getDeckName(),
                card.getFrontSide(),
                card.getBackSide(),
                arrayToJson(card.getTags())
        );
    }

    private String arrayToJson(Object[] arr) {
        JSONArray output = new JSONArray();
        for (Object curr : arr) {
            output.put(curr);
        }
        return output.toString();
    }

}
