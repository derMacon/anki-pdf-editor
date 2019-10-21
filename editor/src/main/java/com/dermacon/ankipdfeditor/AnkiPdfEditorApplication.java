package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.TerminalLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	/**
	 * Main method that launches the spring controller / api and launches the
	 * terminal ui
	 * @param args command line args
	 */
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnkiPdfEditorApplication.class);
		builder.headless(false);
		builder.run(args);

		new TerminalLauncher().run();
	}
}
