package com.dermacon.ui;

import javafx.application.Application;
import javafx.application.Platform;

import java.util.function.Consumer;

public class Mainmain {
    public static void main(String[] args) {
        CheckedConsumer<String> callback = deck -> System.out.println("deckkkk: " + deck);

        FXDeckChooser.setCallback(callback);
        FXDeckChooser.setDeckNames("1", "3", "2");
        Application.launch(FXDeckChooser.class, new String[0]);
//        Platform.runLater(() -> deckChooser.main(new String[0]));
    }
}
