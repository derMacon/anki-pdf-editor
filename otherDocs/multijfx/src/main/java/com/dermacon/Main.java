package com.dermacon;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // launch fst fxml
                new Thread(() -> Initializer.main(args)).start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        // launch snd fxml
//        new Thread(() -> App.main(args)).start();
        System.out.println("jo");
        new App().start(new Stage());

    }
}
