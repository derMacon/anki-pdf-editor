package com.dermacon.ankipdfeditor.apiController;

import java.util.function.Consumer;

public class MainShortcut {
    public static void main(String[] args) {
//        System.out.println("test");
//        ApiController.chooseDeckTest();

        String[] decks = new String[] {"1", "2", "testdeck", "3asdfasdfasdfasdf", "4", "5", "6", "7"};

        Consumer<String> callback = deck -> System.out.println("deck:: " + deck);
        DeckChooser deckChooser = new DeckChooser(decks, callback);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deckChooser.createAndShowGUI(decks, callback);
            }
        });
    }
}
