package com.dermacon.ankipdfeditor.ankiApi.request;

import com.dermacon.ankipdfeditor.ankiApi.response.AddNoteResponse;
import com.dermacon.ankipdfeditor.data.card.Card;
import org.json.JSONArray;

import java.lang.reflect.Type;


/**
 * Anki request to push a card object to the anki api.
 */
public class AddNoteAnkiRequest extends AnkiRequest {

    /**
     * Response type of the request.
     */
    private static final Type RESPONSE_TYPE = AddNoteResponse.class;

    /**
     * Json template for the request.
     * template has placeholders for:
     * version, deckName, Front, Back, tags
     */
    private static final String JSON_TEMPLATE = "{\n"
            + "\"action\": \"addNotes\",\n"
            + "\"version\": %s,\n"
            + "\"params\": {\n"
            + "  \"notes\": [ \n"
            + "    {\n"
            + "      \"deckName\": \"%s\",\n"
            + "      \"modelName\": \"Basic\",\n"
            + "      \"fields\": {\n"
            + "        \"Front\": \"%s\",\n"
            + "        \"Back\": \"%s\"\n"
            + "      },\n"
            + "      \"options\": {\n"
            + "        \"allowDuplicate\": false\n"
            + "      },\n"
            + "      \"tags\": %s\n"
            + "    } \n"
            + "  ]\n"
            + "}\n"
            + "}";


    /**
     * Card object that should be transmitted.
     */
    private Card card;

    /**
     * Constructor.
     * @param card card object to push to the anki api.
     */
    public AddNoteAnkiRequest(final Card card) {
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
                removeExtension(card.getDeckName()),
                card.getFrontSide(),
                card.getBackSide(),
                arrayToJson(card.getTags())
        );
    }

    /**
     * Parses the given object array to a transmittable json array.
     * @param arr array to parse
     * @return transmittable json array
     */
    private String arrayToJson(final Object[] arr) {
        JSONArray output = new JSONArray();
        for (Object curr : arr) {
            output.put(curr);
        }
        return output.toString();
    }

}
