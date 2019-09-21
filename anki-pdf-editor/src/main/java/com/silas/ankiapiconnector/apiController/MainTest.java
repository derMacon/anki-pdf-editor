package com.silas.ankiapiconnector.apiController;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        AnkiApiConnectorApplication.selectLastProject();
    }
}
