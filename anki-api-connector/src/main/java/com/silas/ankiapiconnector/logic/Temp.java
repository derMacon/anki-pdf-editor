package com.silas.ankiapiconnector.logic;

public class Temp {
    public static void main(String[] args) {
        Card c = new Card("deck", "front", "back", new String[0]);
        System.out.println(Card.parseImg("asdf<123>"));
    }
}
