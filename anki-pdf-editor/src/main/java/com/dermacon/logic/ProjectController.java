package com.dermacon.logic;

import java.io.IOException;

public class ProjectController {
    private static final String API_URL = "http://localhost:8080/";
    private static final String PROJECT_INFO_COMMAND = API_URL + "getProjectInfo";
    private static final String NEW_DECK_COMMAND = API_URL + "selectDeck";
    private static final String NEW_PDF_COMMAND = API_URL + "selectNewPdf";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";
    // opens a node server on port 9000
    private static final String OPEN_PDF_VIEWER_COMMAND = "cd %s && http-server -p 9000";
    private static final String PDF_SERVER_URL = "http://127.0.0.1:9000/";
    private static final String ANKI_START_COMMAND = "anki";

    private static final int API_PORT = 8080;

    private ProjectInfo projectInfo;

    public ProjectController() throws IOException {
        this.projectInfo = new ProjectInfo();
    }

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void pushToAnki() {

    }


}
