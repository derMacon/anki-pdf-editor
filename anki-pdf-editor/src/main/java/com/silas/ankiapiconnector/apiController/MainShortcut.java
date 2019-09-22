package com.silas.ankiapiconnector.apiController;

public class MainShortcut {
    public static void main(String[] args) {
//        System.out.println("test");
//        ApiController.chooseDeckTest();

        String[] decks = new String[] {"1", "2", "testdeck", "3asdfasdfasdfasdf", "4", "5", "6", "7"};

        DeckChooser deckChooser = new DeckChooser(decks);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deckChooser.createAndShowGUI(decks);
            }
        });
    }
}
