package com.dermacon.ankipdfeditor.ui;


import com.dermacon.ankipdfeditor.FxmlApp;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import com.dermacon.ankipdfeditor.data.project.ProjectController;
import javafx.application.Application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalUI implements UserInterface {

//    private static final String NEW_TERMINAL_COMMAND = "xterm vim %s";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim -N -u %s %s";

    private AnkiConnector ankiConnector;
    private ProjectController projectController;

    public TerminalUI() throws IOException {
        projectController = new ProjectController();
        ankiConnector = new AnkiConnector();
    }

    @Override
    public void openEditor() throws IOException {
        // open vim
        String pathToDeckFile = projectController.getProjectInfo().getDeck().getPath();
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
                + "Type:\n"
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
            chooseDeck(AnkiConnector.getPossibleDecks());
        } else {
            projectController.setPdf(openFileChooser());
        }
    }

    private void chooseDeck(String[] decks) {
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
    @Override
    public void pushToAnki() throws IOException {
        ankiConnector.pushToAnki(this.projectController.getProjectInfo());
    }

    @Override
    public void saveProjHistory() throws IOException {
        projectController.saveProjHistory();
    }

    @Override
    public ProjectController getProjectController() {
        return this.projectController;
    }
}
