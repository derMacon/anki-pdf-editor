package com.silas.ankiapiconnector.apiController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AnkiApiConnectorApplication {

	private static final int API_PORT = 8080;
	private static final int GUI_PORT = 3000;
	private static final String KILL_PROCESS_TEMPLATE = "kill $(lsof -t -i:%d)";


	public static void main(String[] args) throws IOException {
		killPorts();
		startAnki();
	    SpringApplication.run(AnkiApiConnectorApplication.class, args);
	    startGui();
	}

	private static void killPorts() throws IOException {
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, API_PORT));
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, GUI_PORT));
	}

	private static void startAnki() throws IOException {
		Runtime.getRuntime().exec("anki");
	}

	private static void startGui() throws IOException {
		Runtime.getRuntime().exec("npm start --prefix src/main/js/frontend/");
	}

}
