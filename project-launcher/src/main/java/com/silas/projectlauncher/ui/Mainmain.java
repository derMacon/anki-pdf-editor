package com.silas.projectlauncher.ui;

import javafx.application.Application;

public class Mainmain {
    public static void main(String[] args) {
        CheckedConsumer<String> callback = deck -> System.out.println("deckkkk: " + deck);

        FXDeckChooser.setCallback(callback);
        FXDeckChooser.setDeckNames("1", "3", "2");
        Application.launch(FXDeckChooser.class, new String[0]);
//        Platform.runLater(() -> deckChooser.main(new String[0]));
    }
}
