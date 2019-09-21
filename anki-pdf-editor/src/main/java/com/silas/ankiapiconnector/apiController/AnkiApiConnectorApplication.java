package com.silas.ankiapiconnector.apiController;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AnkiApiConnectorApplication {

	private static final int API_PORT = 8080;
	private static final int GUI_PORT = 3000;
	private static final String KILL_PROCESS_TEMPLATE = "kill $(lsof -t -i:%d)";

	private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
	private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");
	private static final String ANKI_START_COMMAND = "anki";
	private static final String FRONTEND_START_COMMAND = "npm start --prefix src/main/js/frontend/";


	public static void main(String[] args) throws IOException {
		killPorts();
		startAnki();
	    SpringApplication.run(AnkiApiConnectorApplication.class, args);
	    selectLastProject();
	    startGui();
	}

	public static void selectLastProject() throws IOException {
		int n_lines = 2;
		int counter = 0;
		String lastLines = "";
		ReversedLinesFileReader object = new ReversedLinesFileReader(PROJ_HISTORY);
		while(counter < n_lines) {
			lastLines += object.readLine();
			counter++;
		}

		new ProjectInfo(lastLines);
	}

	private static void killPorts() throws IOException {
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, API_PORT));
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, GUI_PORT));
	}

	private static void startAnki() throws IOException {
		Runtime.getRuntime().exec(ANKI_START_COMMAND);
	}

	private static void startGui() throws IOException {
		Runtime.getRuntime().exec(FRONTEND_START_COMMAND);
	}

}
