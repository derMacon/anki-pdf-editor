package com.dermacon.ankipdfeditor.ankiApi.request.consumer;

import com.dermacon.ankipdfeditor.data.card.Card;
import org.json.JSONArray;

import java.util.List;

/**
 * Anki request to push a card object to the anki api.
 */
public class AddNotesAnkiRequest extends ConsumingRequest {

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
            + "    %s\n"
            + "  ]\n"
            + "}\n"
            + "}";

    private static final String NOTE_JSON =
            "    {\n"
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
            + "    },\n";

    /**
     * Card object that should be transmitted.
     */
    private List<Card> cards;

    /**
     * Constructor.
     * @param cards card object to push to the anki api.
     */
    public AddNotesAnkiRequest(final List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toJson() {
        StringBuilder out = new StringBuilder();
        for (Card card : this.cards) {
            out.append(createArrayElement(card));
        }
        out.setLength(out.length() - 2); // delete last comma

        return String.format(JSON_TEMPLATE,
                String.valueOf(this.version),
                out.toString()
        );
    }


    private String createArrayElement(Card card) {
        return String.format(NOTE_JSON,
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
