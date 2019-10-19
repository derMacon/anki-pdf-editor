package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.AnkiConnectCardModel;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * todo WIP
 * Deck builder for the card.
 */
public class CardStackBuilder {

    private List<Card> stack = new LinkedList<>();

    public void addCard(String info) {
        AnkiConnectCardModel cardModel = new Gson().fromJson(info, AnkiConnectCardModel.class);
        System.out.println(info);
    }

    public List<Card> getStack() {
        return this.stack;
    }

}
