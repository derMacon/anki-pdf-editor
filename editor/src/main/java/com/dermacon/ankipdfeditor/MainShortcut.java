package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.data.card.Card;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;

import java.io.IOException;
import java.util.List;

public class MainShortcut {
    public static void main(String[] args) throws IOException {
        List<Card> cards =  AnkiConnector.getCardsFromDeck("Betriebssysteme");
        System.out.println(cards);
    }
}
