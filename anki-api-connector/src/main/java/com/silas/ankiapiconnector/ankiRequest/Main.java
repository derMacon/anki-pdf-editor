package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;
import com.silas.ankiapiconnector.logic.Card;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Card card = new Card("TestDeck", "Basic", "hmm hmm", "back", new String[] {});
        System.out.println(new Gson().toJson(card));

        Request requ = new AddNoteRequest(card);
        System.out.println(new Gson().toJson(requ));

        AnkiConnector connector =  new AnkiConnector();
        Response resp = connector.request(requ);
        System.out.println(resp);


//        Response resp = new SimpleReply("simple", null);
//        System.out.println(new Gson().toJson(resp));


    }
}