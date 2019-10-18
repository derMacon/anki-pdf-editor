package com.dermacon.ankipdfeditor.data.card;

import com.dermacon.ankipdfeditor.ankiApi.response.function.NotesInfoReply;

import java.util.LinkedList;
import java.util.List;

/**
 * todo WIP
 * Deck builder for the card.
 */
public class CardStackBuilder {

    private List<Card> stack = new LinkedList<>();

    public void addCard(Object info) {
        System.out.println(info);
    }

    public List<Card> getStack() {
        return this.stack;
    }

}
