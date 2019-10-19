package com.dermacon.ankipdfeditor.ui;


import com.dermacon.ankipdfeditor.FxmlApp;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import com.dermacon.ankipdfeditor.data.project.ProjectController;
import com.dermacon.ankipdfeditor.export.Exporter;
import com.dermacon.ankipdfeditor.export.ExporterFactory;
import com.dermacon.ankipdfeditor.export.Formating;
import javafx.application.Application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalUI implements UserInterface {

//    private static final String NEW_TERMINAL_COMMAND = "xterm vim %s";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim -N -u %s %s";

    private ProjectController projectController;

    public TerminalUI() throws IOException {
        projectController = new ProjectController();
    }

    @Override
    public void pushToAnki() throws IOException {
        AnkiConnector.pushToAnki(this.projectController.getProjectInfo());
    }

    @Override
    public void saveProjHistory() throws IOException {
        projectController.saveProjHistory();
    }

    @Override
    public ProjectController getProjectController() {
        return this.projectController;
    }

    @Override
    public void exportStack() throws IOException {
        String deck = deckSelection();
        Formating formatingChoice = formatSelection();

        generateExportFile(deck, formatingChoice);

        System.out.println(TerminalLauncher.DELIMITER_MAIN + "export successful");
    }

    @Override
    public void openEditor() throws IOException {
        // open vim
        String pathToDeckFile = projectController.getProjectInfo().getDeckFile().getPath();
        String pathToVimrc = projectController.getProjectInfo().getSessionVimrc().getPath();
        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToVimrc, pathToDeckFile);
        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void openPdfViewer() {
        new FxmlApp().launchPdf(projectController);
    }

    @Override
    public void updateProjectInfo() throws IOException {
        System.out.print(
                TerminalLauncher.DELIMITER_MAIN
                + "type:\n"
                + "  * 1: deck\n"
                + "  * 2: pdf\n"
                + TerminalLauncher.DELIMITER_SEC
                + "input: "
        );
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.matches("1|2")) {
            System.out.print("try again: ");
            input = scanner.nextLine();
        }

        if (input.equals("1")) {
            chooseDeck();
        } else {
            projectController.setPdf(openFileChooser());
        }
    }

    private void chooseDeck() throws IOException {
        String deckname = userSelection(displayOptions());
        projectController.setDeck(deckname + ".anki");
    }

    // todo maybe delete this
    private void openDeckChooser(String[] decks) {
        FXDeckChooser.setDeckNames(decks);
        FXDeckChooser.setCallback(deckName -> projectController.setDeck(deckName + ".anki"));
        Application.launch(FXDeckChooser.class, new String[0]);
    }

    private File openFileChooser() throws IOException {
        JFileChooser chooser = new JFileChooser(projectController.getProjectInfo().getPdf());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        File output = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            output = chooser.getSelectedFile();
        }

        if (output == null) {
            throw new IOException("user aborted pdf selection process");
        }

        return output;
    }

    private void generateExportFile(String deckname, Formating formating) throws IOException {
        File deckDir = projectController.getProjectInfo().getDeckDir();
        Exporter exporter = ExporterFactory.createExporter(deckDir, formating);
        exporter.createOutput(deckname);
    }

    private Formating formatSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                TerminalLauncher.DELIMITER_MAIN
                        + "format:\n"
                        + "  * 1: pdf (WIP)\n"
                        + "  * 2: html (WIP)\n"
                        + TerminalLauncher.DELIMITER_SEC
                        + "input: "
        );

        int choice = Integer.parseInt(scanner.nextLine());
        while (choice <= 0 || choice > Formating.values().length) {
            System.out.print("try again: ");
            choice = Integer.parseInt(scanner.nextLine());
        }

        return Formating.values()[choice - 1];
    }

    private String deckSelection() throws IOException {
        String[] decknames = displayOptions();
        String deckname = userSelection(decknames);
        return deckname;
    }

    private String[] displayOptions() throws IOException {
        StringBuilder deckCollection = new StringBuilder(TerminalLauncher.DELIMITER_MAIN + "deck selection:\n");
        String[] decknames = AnkiConnector.getDeckNames();
        String deckname_format = "  * %d: %s\n";
        for (int i = 0; i < decknames.length; i++) {
            deckCollection.append(String.format(deckname_format, i + 1, decknames[i]));
        }
        deckCollection.append(TerminalLauncher.DELIMITER_SEC + "input: ");
        System.out.print(deckCollection);

        return decknames;
    }

    private String userSelection(String[] decknames) {
        Scanner scanner = new Scanner(System.in);
        int deckoption = Integer.parseInt(scanner.nextLine());
        while(deckoption <= 0 || deckoption > decknames.length) {
            System.out.print("try again\ninput: ");
            deckoption = Integer.parseInt(scanner.nextLine());
        }
        return decknames[deckoption - 1];
    }
}
