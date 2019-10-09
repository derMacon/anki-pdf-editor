package com.dermacon.ankipdfeditor;

import com.dermacon.ankipdfeditor.ui.TerminalLauncher;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;

@SpringBootApplication
public class AnkiPdfEditorApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(AnkiPdfEditorApplication.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnkiPdfEditorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);


		new TerminalLauncher().run();
	}
}
