package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.App;
import javafx.scene.image.Image;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	public static void main(String[] args) {
        loadSpringController(args);
        App app = new App();
        app.main(args);

	}

	private static void loadSpringController(String[] args) {
		// use of builder necessary otherwise awt.headlessexception when opening filechooser
		// https://stackoverflow.com/questions/51004447/spring-boot-java-awt-headlessexception
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnkiApiConnectorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}
}
