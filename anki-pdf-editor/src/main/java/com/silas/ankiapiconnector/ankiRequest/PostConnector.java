package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostConnector {

    private static final Integer DEFAULT_PORT = 8765;

    private HttpURLConnection connection = null;
    private Gson gson = new Gson();

    public PostConnector(Integer port) throws IOException {
        setupConnection(port, "");
    }

    public PostConnector(Integer port, String command) throws IOException {
        setupConnection(port, "/" + command);
    }

    private void setupConnection(Integer port, String command) throws IOException {
        URL url = new URL ("http://localhost:" + port + command);
        connection = (HttpURLConnection)url.openConnection();
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
    public Response request(Request request) throws IOException {
        String jsonResponse = request(request.toJson());
        return gson.fromJson(jsonResponse, request.getResponseType());
    }

    public String request(String jsonInputString) throws IOException {

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
