package com.silas.ankiapiconnector.apiController;

import java.io.IOException;

public class ProjectInfo {
    private String deck;
    private String pdf;

    public ProjectInfo(String input) throws IOException {
        String[] data = input.split("\n");
        for(String line : data) {
            interpretLine(line);
        }
    }

    private void interpretLine(String line) throws IOException {
        // todo maybe check for pattern... fill in info in Exception clause
        if (line.startsWith("deck")) {
            this.deck = line.split(":")[1];
        } else if (line.startsWith("pdf")) {
            this.pdf = line.split(":")[1];
        } else {
            throw new IOException("input line does not match pattern");
        }
    }
}
