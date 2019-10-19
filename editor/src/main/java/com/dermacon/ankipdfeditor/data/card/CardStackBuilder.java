package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.AnkiConnectCardModel;
import com.dermacon.ankipdfeditor.ankiApi.response.function.NotesInfoReply;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * todo WIP
 * Deck builder for the card.
 */
public class CardStackBuilder {

    private List<Card> stack = new LinkedList<>();

    public void addCard(NotesInfoReply reply) {
        System.out.println(reply);
    }

    public List<Card> getStack() {
        return this.stack;
    }

}
