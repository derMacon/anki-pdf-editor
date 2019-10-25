package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.AnkiConnectCardModel;
import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.Field;
import com.dermacon.ankipdfeditor.ankiApi.response.function.NotesInfoReply;
import java.util.LinkedList;
import java.util.List;

/**
 * todo WIP
 * Deck builder for the card.
 */
public class CardStackBuilder {

    private String deckname;
    private List<Card> stack = new LinkedList<>();

    public CardStackBuilder(String deckname) {
        this.deckname = deckname;
    }

    public void addCard(NotesInfoReply reply) {
        AnkiConnectCardModel card = reply.getResult().get(0);
        String front = card.getFields().get("Front").getValue();
        String back = card.getFields().get("Back").getValue();
        String[] tags = card.getTags().toArray(new String[0]);
        stack.add(new Card(deckname, front, back, tags));
    }

    public List<Card> getStack() {
        return this.stack;
    }

}
