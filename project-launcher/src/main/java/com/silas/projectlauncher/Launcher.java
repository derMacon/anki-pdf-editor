package com.silas.projectlauncher;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Launcher {

    private static final int API_PORT = 8080;
    private static final int GUI_PORT = 3000;
    private static final String KILL_PROCESS_TEMPLATE = "kill $(lsof -t -i:%d)";

    public void runAll() throws IOException {
//        killPorts();
//        startAnki();
//        startBackendApi();
        choosePdfFile();
//        startGui();
//        waitForInput();
//        killPorts();
    }

    private void killPorts() throws IOException {
        Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, API_PORT));
        Runtime.getRuntime().exec(String.format(KILL_PROCESS_TEMPLATE, GUI_PORT));
    }

    private void startAnki() throws IOException {
        Runtime.getRuntime().exec("anki");
    }

    private void startBackendApi() throws IOException {
        System.out.println("Start Backend Api");
        String backEndJar_path = "src/main/resources/anki-api-connector-0.0.1-SNAPSHOT.jar";
        Process p = Runtime.getRuntime().exec("java -jar " + backEndJar_path);
        // Then retreive the process output
        InputStream in = p.getInputStream();
        InputStream err = p.getErrorStream();
    }

    private void choosePdfFile() {
        System.out.println("Choose pdf file");
        File selectedPdf = openFileChooser();
        selectPdfInApi(selectedPdf);
    }

    private File openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        File output = null;
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            output = chooser.getSelectedFile();
        }
        return output;
    }

    private void selectPdfInApi(File selectedPdf) {
        System.out.println("select in api " + selectedPdf.getAbsolutePath());
    }

    private void startGui() {
        System.out.println("start gui");
    }

    private void waitForInput() throws IOException {
        Scanner in = new Scanner(System.in);
        String curr;
        do {
            System.out.println("Type \"exit\" to shutdown program (Gui + server)");
        } while(!in.nextLine().toLowerCase().equals("exit"));
    }



}
