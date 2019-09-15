package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.silas.ankiapiconnector.ankiRequest.request.Request;
import com.silas.ankiapiconnector.ankiRequest.response.Response;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class AnkiConnector {

    private HttpURLConnection connection = null;
    private Gson gson = new Gson();

    public AnkiConnector(Integer port) throws IOException {
        setupConnection(port);
    }

    private void setupConnection(Integer port) throws IOException {
        URL url = new URL ("http://localhost:" + port);
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
        String jsonResponse = sendRequest(request);
//        Response responseObj = getResponseObj(String.class, jsonResponse);

//        System.out.println(responseObj);
//        return responseObj;
        return null;
    }

    public String sendRequest(String jsonInputString) throws IOException {

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


    public String sendRequest(Request request) throws IOException {
        String jsonInputString = gson.toJson(request);

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

//
//    private static <T> Response<T> getResponseObj(Class responseType, String json) {
//        // complete response type containing the error field
//        Type completeResponseType = TypeToken.getParameterized(Response.class, responseType).getType();
//        return new Gson().fromJson(json, completeResponseType);
//    }
//



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
