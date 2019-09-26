package com.silas.ankiapiconnector.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TerminalUi {

    private static final String UPDATE_PROJECT_QUERY =
            "Current project: %s\n"
            + "Type\n"
            + "  - x to exit\n"
            + "  - d to choose a new deck\n"
            + "  - f to open a new pdf file\n\n";

    private static final String API_URL = "http://localhost:8080/";
    private static final String PROJECT_INFO_COMMAND = API_URL + "getProjectInfo";
    private static final String NEW_DECK_COMMAND = API_URL + "selectDeck";
    private static final String NEW_PDF_COMMAND = API_URL + "selectNewPdf";


    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String output;

        System.out.println("Anki-Editor - version 1.0\n");

        do {
            System.out.println(String.format(UPDATE_PROJECT_QUERY, getRequest(PROJECT_INFO_COMMAND)));
            output = runQuery(scanner.nextLine());
        } while (!output.equals("error") && !output.equals("exit"));

        System.out.println("shutdown");
        System.exit(0);
    }

    private String runQuery(String choice) throws Exception {
        String output;
        switch (choice) {
            case "x":
                output = "exit";
                break;
            case "d":
                output = "new deck";
                getRequest(NEW_DECK_COMMAND);
                break;
            case "f":
                output = "new pdf";
                getRequest(NEW_PDF_COMMAND);
                break;
            default: output = "error";
        }
        return output;
    }

    /**
     * https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getRequest(String urlToRead) throws Exception {
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
