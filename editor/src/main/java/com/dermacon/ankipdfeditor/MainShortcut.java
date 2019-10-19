package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ankiApi.PostConnector;
import com.dermacon.ankipdfeditor.ankiApi.request.function.NotesInfoRequest;
import com.dermacon.ankipdfeditor.ankiApi.response.function.NotesInfoReply;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;

import java.io.IOException;

public class MainShortcut {
    public static void main(String[] args) throws IOException {
        AnkiConnector.getCardsFromDeck("Betriebssysteme");
        PostConnector p = new PostConnector(4567);
        NotesInfoReply r = p.jsonRequest(new NotesInfoRequest(42L));
        System.out.println(r);
    }
}
