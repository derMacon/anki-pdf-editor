package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.TerminalLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	private static final String MANUAL_RES_PATH = "src/main/resources/com/dermacon/ankipdfeditor/manual.pdf";

	public static void main(String[] args) {
		SpringApplication.run(AnkiPdfEditorApplication.class, args);
//		System.out.println(new File(MANUAL_RES_PATH).exists());
		new TerminalLauncher().run();
	}
}
