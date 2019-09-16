package com.silas.ankiapiconnector.ankiRequest.response;

import com.silas.ankiapiconnector.ankiRequest.AnkiConnector;

import java.io.IOException;

public class Temp {

    public static void main(String[] args) throws IOException {
        String in = "{\n" +
                "\"action\": \"addNotes\",\n" +
                "\"version\": 6,\n" +
                "\"params\": {\n" +
                "  \"notes\": [ \n" +
                "    {\n" +
                "      \"deckName\": \"TestDeck\",\n" +
                "      \"modelName\": \"Basic\",\n" +
                "      \"fields\": {\n" +
                "        \"Front\": \"why?why.\",\n" +
                "        \"Back\": \"because!\"\n" +
                "      },\n" +
                "      \"options\": {\n" +
                "        \"allowDuplicate\": true\n" +
                "      },\n" +
                "      \"tags\": []\n" +
                "    } \n" +
                "  ]\n" +
                "}\n" +
                "}";


//        Request requ = new AddNoteRequest(\"addNote\", 6, new Params(card));
//        System.out.println(new Gson().toJson(requ));
//
        AnkiConnector connector =  new AnkiConnector(8765);
        connector.sendRequest(in);
    }
}
