package com.dermacon.ankipdfeditor.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXDeckChooser extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static CheckedConsumer<String> callback;
    private static String[] deckNames;


    public static void setCallback(CheckedConsumer<String> callback_) {
        callback = callback_;
    }

    public static void setDeckNames(String... deckNames_) {
        deckNames = deckNames_;
    }

    final Button btnExistentDeck = new Button ("Send");
    final Button btnNewDeck = new Button ("Send");
    final TextField subject = new TextField("");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ComboBoxSample");
        Scene scene = new Scene(new Group(), 400, 100);

        final ComboBox emailComboBox = new ComboBox();
//        emailComboBox.getItems().addAll(
//                "jacob.smith@example.com",
//                "isabella.johnson@example.com",
//                "ethan.williams@example.com",
//                "emma.jones@example.com",
//                "michael.brown@example.com"
//        );

        emailComboBox.getItems().addAll(deckNames);

        final ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(
                "Highest",
                "High",
                "Normal",
                "Low",
                "Lowest"
        );

        priorityComboBox.setValue("Normal");

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("To: "), 0, 0);
        grid.add(emailComboBox, 1, 0);
        grid.add(new Label("Subject: "), 0, 1);
        grid.add(subject, 1, 1, 1, 1);

        grid.add(btnExistentDeck, 2, 0);
        grid.add(btnNewDeck, 2, 1);


        btnExistentDeck.setOnAction(e -> {
            try {
                Object selectedItem = emailComboBox.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    showAlert("user has not selected any deckname");
                } else {
                    callback.accept(selectedItem.toString());
                }
            } catch (IOException ex) {
                showAlert(ex.getMessage());
            }

            Platform.exit();
        });

        btnNewDeck.setOnAction(e -> {
            try {
                String deckName = subject.getText();
                if (deckName.isEmpty()) {
                    showAlert("deck name is empty");
                } else {
                    callback.accept(deckName);
                }
            } catch (IOException ex) {
                showAlert(ex.getMessage());
            }

            Platform.exit();
    });


        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
