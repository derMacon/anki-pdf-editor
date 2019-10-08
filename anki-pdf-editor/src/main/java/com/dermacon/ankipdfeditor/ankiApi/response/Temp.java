package com.dermacon.ankipdfeditor.ankiApi.response;


//import com.dermacon.ankiApi.PostConnector;

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
//        PostConnector connector = new PostConnector(8765);
//        connector.jsonRequest(in);
    }
}
