package com.dermacon.ankipdfeditor.ankiApi;

import com.dermacon.ankipdfeditor.ankiApi.request.AnkiRequest;
import com.dermacon.ankipdfeditor.ankiApi.response.AnkiResponse;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostConnector {

    private static final Integer DEFAULT_PORT = 8765;

    private String url = null;
    private HttpURLConnection connection = null;
    private Gson gson = new Gson();

    public PostConnector(Integer port) throws IOException {
        setupConnection(port, "");
    }

    public PostConnector(Integer port, String urlPostfix) throws IOException {
        setupConnection(port, "/" + urlPostfix);
    }

    private void setupConnection(Integer port, String command) throws IOException {
        url = "http://localhost:" + port + command;
        connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
    }

    /**
     * Usefull resources:
     * - StackOverflow: https://stackoverflow.com/questions/7181534/http-post-using-json-in-java
     *
     * @throws IOException
     */
    public AnkiResponse jsonRequest(AnkiRequest request) throws IOException {
        String jsonResponse = jsonRequest(request.toJson());
        return gson.fromJson(jsonResponse, request.getResponseType());
    }

    public String jsonRequest(String jsonInputString) throws IOException {

        try (OutputStream os = this.connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String output = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            output = response.toString();
        }
        return output;

    }

    /**
     * http://zetcode.com/java/getpostrequest/
     *
     * @param urlParameters
     * @throws IOException
     */
    public void urlRequest(String urlParameters) throws IOException {
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(url);
            connection = (HttpURLConnection) myurl.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Java client");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

        } finally {

            connection.disconnect();
        }
    }

}
