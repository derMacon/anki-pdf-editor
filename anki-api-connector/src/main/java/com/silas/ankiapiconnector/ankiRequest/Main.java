package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.ankiRequest.request.Params;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;
import com.silas.ankiapiconnector.ankiRequest.response.SimpleReply;
import com.silas.ankiapiconnector.logic.Card;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Card card = new Card("TestDeck", "Basic", "hmm", "back", new String[] {"test"});
        System.out.println(new Gson().toJson(card));

        Request requ = new AddNoteRequest("addNote", 6, new Params(card));
        System.out.println(new Gson().toJson(requ));

        AnkiConnector connector =  new AnkiConnector(8765);
        connector.request(requ);

//        Response resp = new SimpleReply("simple", null);
//        System.out.println(new Gson().toJson(resp));


    }
}
