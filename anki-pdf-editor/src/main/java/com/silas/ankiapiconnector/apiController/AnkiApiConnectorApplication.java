package com.silas.ankiapiconnector.apiController;

import com.silas.ankiapiconnector.ankiRequest.PostConnector;
import com.silas.ankiapiconnector.apiController.projectInfo.ProjectInfo;
import com.silas.ankiapiconnector.apiController.projectInfo.ProjectInfoContainer;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AnkiApiConnectorApplication {

	private static final int API_PORT = 8080;
	private static final int GUI_PORT = 3000;
	private static final String KILL_PROCESS_COMMAND = "kill $(lsof -t -i:%d)";

	private static final String LAST_DOCS_DIR = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/";
	private static final File PROJ_HISTORY = new File(LAST_DOCS_DIR + ".projHistory");
	private static final String ANKI_START_COMMAND = "anki";
	private static final String FRONTEND_START_COMMAND = "npm start --prefix src/main/js/frontend/";


	public static void main(String[] args) throws IOException {
		killPorts();
//		startAnki();

		// use of builder necessary otherwise awt.headlessexception when opening filechooser
		// https://stackoverflow.com/questions/51004447/spring-boot-java-awt-headlessexception
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AnkiApiConnectorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

//		selectLastProject();
	    startGui();
	}

	public static void selectLastProject() throws IOException {
	    initSpringApi(ProjectInfoContainer.getProjectInfo());
//	    initSpringApi(getLastProjectInfo());
	}

	private static void initSpringApi(ProjectInfo projectInfo) throws IOException {
		new PostConnector(8080, "initProjectInfo").urlRequest(projectInfo.toUrlParameters());
	}

	private static void killPorts() throws IOException {
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_COMMAND, API_PORT));
		Runtime.getRuntime().exec(String.format(KILL_PROCESS_COMMAND, GUI_PORT));
	}

	private static void startAnki() throws IOException {
		Runtime.getRuntime().exec(ANKI_START_COMMAND);
	}

	private static void startGui() throws IOException {
		Runtime.getRuntime().exec(FRONTEND_START_COMMAND);
	}

}
