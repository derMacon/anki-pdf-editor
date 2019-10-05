package com.dermacon.ui;


import com.dermacon.GuiLauncher;
import com.dermacon.data.project.AnkiConnector;
import com.dermacon.data.project.ProjectController;
import com.dermacon.data.project.ProjectInfo;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Consumer;

public class TerminalUI implements UserInterface {

//    private static final String NEW_TERMINAL_COMMAND = "xterm vim %s";
    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";

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
        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToDeckFile);
        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void openPdfViewer() {
        GuiLauncher.launch(projectController);
    }

    @Override
    public void updateProjectInfo() throws IOException {
        // todo
        System.out.println("todo");
        // controller.setProjectInfo(...);

        System.out.print("choose:\n1: deck\n2: pdf\n\ninput: ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.matches("1|2")) {
            System.out.print("try again: ");
            input = scanner.nextLine();
        }

        if (input.equals("1")) {
            System.out.println("decks:");
            System.out.println(AnkiConnector.getPossibleDecks());
//            chooseDeck(AnkiConnector.getPossibleDecks());
        } else {
            projectController.setPdf(openFileChooser());
        }
    }

    private void chooseDeck(String[] decks) {
        Consumer<String> callback = deck -> projectController.setDeck(deck);
        DeckChooser deckChooser = new DeckChooser(decks, callback);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deckChooser.createAndShowGUI(decks, callback);
            }
        });
    }

    private File openFileChooser() throws IOException {
        JFileChooser chooser = new JFileChooser(projectController.getProjectInfo().getPdf());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        File output = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            output = chooser.getSelectedFile();
        }

        if (output == null) {
            throw new IOException("user aborted pdf selection process");
        }

        return output;
    }
    @Override
    public void push() throws IOException {
        ankiConnector.pushToAnki(this.projectController.getProjectInfo());
    }

    @Override
    public void save() throws IOException {
        projectController.saveProjHistory();
    }

    @Override
    public ProjectController getProjectController() {
        return this.projectController;
    }
}
