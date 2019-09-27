package com.silas.ankiapiconnector.ui;

import com.google.gson.Gson;
import com.silas.ankiapiconnector.apiController.ProjectInfo;
import com.silas.ankiapiconnector.logic.Card;
import com.silas.ankiapiconnector.logic.CardFactory;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class TerminalLogic {

    private static final String UPDATE_PROJECT_QUERY =
            "Current project: %s\n"
            + "Type\n"
            + "  - e to edit project properties\n"
            + "  - wq to save with exit\n"
            + "  - w to save without exit\n\n";

    private static final String API_URL = "http://localhost:8080/";
    private static final String PROJECT_INFO_COMMAND = API_URL + "getProjectInfo";
    private static final String NEW_DECK_COMMAND = API_URL + "selectDeck";
    private static final String NEW_PDF_COMMAND = API_URL + "selectNewPdf";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";
    // opens a node server on port 9000
    private static final String OPEN_PDF_VIEWER_COMMAND = "cd %s && http-server -p 9000";
    private static final String PDF_SERVER_URL = "http://127.0.0.1:9000/";

    private ProjectInfo projectInfo;

    public TerminalLogic() throws IOException {
        updateProjectInfo();
    }

    private void updateProjectInfo() throws IOException {
        String requestResponse = getRequest(PROJECT_INFO_COMMAND);
        this.projectInfo = new Gson().fromJson(requestResponse, ProjectInfo.class);
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean shutdown;

        System.out.println("Anki-Editor - version 1.0\n");

        do {
            System.out.println(String.format(UPDATE_PROJECT_QUERY, projectInfo));
            shutdown = runQuery(scanner.nextLine().toLowerCase());
        } while (!shutdown);

        System.out.println("shutdown");
        System.exit(0);
    }

    private boolean runQuery(String choice) throws IOException {
        boolean shutdown = true;
        if (choice.equals("e")) {
            getRequest(NEW_DECK_COMMAND);
            getRequest(NEW_PDF_COMMAND);
            openCurrProject();
            shutdown = false;
        } else if (choice.equals("w")) {
            File currDeckFile = new File(projectInfo.getPdfPath());
            Card[] stack = CardFactory.produceStack(FileUtils.readFileToString(currDeckFile));
        }
        return shutdown;
    }

    public void openCurrProject() throws IOException {
        String requestResponse = getRequest(PROJECT_INFO_COMMAND);
        ProjectInfo info = new Gson().fromJson(requestResponse, ProjectInfo.class);

        // open vim
        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, info.getDeckFile());
        Runtime.getRuntime().exec(openNewTerminalCommand);

        // run pdf-viewer-server
        String pdfViewerPath = new File(System.getProperty("user.dir")).getParent() + "/pdf_viewer/";
        String openPdfViewer = String.format(OPEN_PDF_VIEWER_COMMAND, pdfViewerPath);
        String[] cmd = { "/bin/sh", "-c", openPdfViewer };
        Runtime.getRuntime().exec(cmd);

        // open browser
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(PDF_SERVER_URL));
            } catch (URISyntaxException e) {
                throw new IOException("URI-Exception, " + e.getMessage());
            }
        }
    }

    /**
     * https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getRequest(String urlToRead) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}
