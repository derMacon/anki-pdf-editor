package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.TerminalLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnkiPdfEditorApplication.class, args);
		new TerminalLauncher().run();
	}
}
