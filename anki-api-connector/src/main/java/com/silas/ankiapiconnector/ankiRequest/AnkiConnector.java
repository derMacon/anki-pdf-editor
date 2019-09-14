package com.silas.ankiapiconnector.ankiRequest;

import org.apache.commons.io.IOUtils;
import org.apache.coyote.Request;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AnkiConnector {

    public static void main(String[] args) throws IOException {
        String command = "curl localhost:8765 -X POST -d \"{\\\"action\\\": \\\"deckNames\\\", \\\"version\\\": 6}\"";

        requestBealdung();


    }

    private static void curlFromBash(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("bash", "-c", command);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void iTried() {
//        String command = "curl localhost:8765 -X POST -d \"{\\\"action\": \\\"deckNames\\\", \\\"version\\\": 6}w\"";
//        Process process = Runtime.getRuntime().exec(command);
//        InputStream str = process.getInputStream();
//        String out = IOUtils.toString(str, StandardCharsets.UTF_8);
//        System.out.println(out);
    }

    private static void requestStackOverflow() {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        try {

            HttpPost request = new HttpPost("http://localhost:8765");
            StringEntity params =new StringEntity("{\"action\": \"deckNames\", \"version\": \"6\"} ");
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            //handle response here...
            System.out.println('a');
        }catch (Exception ex) {

            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown();
        }
    }

    private static void requestBealdung() throws IOException {
        URL url = new URL ("http://localhost:8765");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\"action\": \"deckNames\", \"version\": 6}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

    }


}
