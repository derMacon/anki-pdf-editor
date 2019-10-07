package com.dermacon.ankipdfeditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnkiPdfEditorApplication.class, args);
		FxmlApp.main(args);
	}
}
