package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

    public PostConnector(Integer port, String command) throws IOException {
        setupConnection(port, "/" + command);
    }

    private void setupConnection(Integer port, String command) throws IOException {
        url = "http://localhost:" + port + command;
        connection = (HttpURLConnection)new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
    }

    /**
     * Usefull resources:
     * - StackOverflow: https://stackoverflow.com/questions/7181534/http-post-using-json-in-java
     * @throws IOException
     */
    public Response jsonRequest(Request request) throws IOException {
        String jsonResponse = jsonRequest(request.toJson());
        return gson.fromJson(jsonResponse, request.getResponseType());
    }

    public String jsonRequest(String jsonInputString) throws IOException {

        System.out.println("input str: " + jsonInputString);

        try(OutputStream os = this.connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String output = null;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            output = response.toString();
        }
        System.out.println("out: " + output);
        return output;

    }

    /**
     * http://zetcode.com/java/getpostrequest/
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

            System.out.println(content.toString());

        } finally {

            connection.disconnect();
        }
    }



//    private static void curlFromBash(String command) {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        processBuilder.command("bash", "-c", command);
//        try {
//            Process process = processBuilder.start();
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            int exitCode = process.waitFor();
//            System.out.println("\nExited with error code : " + exitCode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
