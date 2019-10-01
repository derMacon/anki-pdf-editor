package com.dermacon.ankipdfeditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	private ImageView imageView;

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public static void main(String[] args) {
		new App().main(args);
//		SpringApplication.run(AnkiPdfEditorApplication.class, args);
        loadSpringController(args);
//		App.main(args);

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		currPdfPage.get().setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/a/a2/Wikimedia_Outreach_test_logo.png"));
//		System.out.println("finito");
	}

	private static void loadSpringController(String[] args) {
		// use of builder necessary otherwise awt.headlessexception when opening filechooser
		// https://stackoverflow.com/questions/51004447/spring-boot-java-awt-headlessexception
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnkiApiConnectorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}
}
