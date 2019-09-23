package com.silas.ankiapiconnector.ankiRequest;

import com.silas.ankiapiconnector.ankiRequest.request.AddNoteRequest;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;
import com.silas.ankiapiconnector.logic.Card;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Card card = new Card("TestDeck", "hmm hmm2", "back", new String[]{"tag"});
        System.out.println(card);
        Request requ = new AddNoteRequest(card);
        System.out.println(requ.toJson());

//        ApiConnector connector =  new ApiConnector();
//        Response resp = connector.request(requ);
//        System.out.println(resp);


//        Response resp = new SimpleReply("simple", null);
//        System.out.println(new Gson().toJson(resp));


    }
}
