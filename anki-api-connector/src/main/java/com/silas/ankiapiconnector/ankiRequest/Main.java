package com.silas.ankiapiconnector.ankiRequest;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("main");
        Request request = new Request("version", 6);
        new AnkiConnector(8765).request(request);
    }
}
