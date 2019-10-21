package com.dermacon.ankipdfeditor.ui;


import com.dermacon.ankipdfeditor.FxmlApp;
import com.dermacon.ankipdfeditor.data.project.AnkiConnector;
import com.dermacon.ankipdfeditor.data.project.ProjectController;
import com.dermacon.ankipdfeditor.data.project.ProjectInfo;
import com.dermacon.ankipdfeditor.export.ExportInfo;
import com.dermacon.ankipdfeditor.export.Exporter;
import com.dermacon.ankipdfeditor.export.ExporterFactory;
import com.dermacon.ankipdfeditor.export.Formating;
import javafx.application.Application;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Terminal ui that processes the queries given by the terminalLauncher.
 */
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
    public void exportAnyStack() throws IOException {
        String deck = deckSelection();
        Formating formatingChoice = formatSelection();

        generateExport(deck, formatingChoice);

        System.out.println(TerminalLauncher.DELIMITER_MAIN + "export successful");
    }

    @Override
    public void exportCurrStack() throws IOException {
        String deck = projectController.getProjectInfo().getDeckName();
        generateExport(deck, Formating.HTML);
    }

    private void generateExport(String deckname, Formating formating) throws IOException {
        ProjectInfo projectInfo = projectController.getProjectInfo();

        ExportInfo exportInfo = new ExportInfo.ExportInfoBuilder()
                .setDeckname(deckname)
                .setMediaPath(projectInfo.getMediaDir())
                .setExportPath(projectInfo.getExportDir())
                .setFormating(formating)
                .build();

        Exporter exporter = ExporterFactory.createExporter(exportInfo);
        exporter.createOutput();
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
                        + "  * 3: export directory\n"
                        + TerminalLauncher.DELIMITER_SEC
                        + "input: "
        );
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.matches("1|2|3")) {
            System.out.print("try again: ");
            input = scanner.nextLine();
        }

        switch (Integer.parseInt(input)) {
            case 1:
                chooseDeck();
                break;
            case 2:
                projectController.setPdf(openFileChooser("pdf"));
                break;
            case 3:
                String dirPath = openDirectoryChooser().getPath() + "/";
                System.out.println("set export path: " + dirPath);
                projectController.getProjectInfo().setExportPath(dirPath);
                break;
        }
    }

    /**
     * Opens a user dialog in the terminal that makes it possible to select a
     * deck.
     * @throws IOException
     */
    private void chooseDeck() throws IOException {
        String deckname = userSelection(displayOptions());
        projectController.setDeck(deckname + ".anki");
    }

    // todo maybe delete this

    // todo delete this
    private void openDeckChooser(String[] decks) {
        FXDeckChooser.setDeckNames(decks);
        FXDeckChooser.setCallback(deckName -> projectController.setDeck(deckName + ".anki"));
        Application.launch(FXDeckChooser.class, new String[0]);
    }

    /**
     * Opens a awt.swing file chooser.
     * @param filetype String containing the filetype which the user should
     *                 be able to select.
     * @return selected file instance.
     * @throws IOException thrown if file io is not working properly
     */
    private File openFileChooser(String filetype) throws IOException {
        JFileChooser chooser = new JFileChooser(projectController.getProjectInfo().getPdf());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                filetype.toUpperCase(), filetype.toLowerCase());
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

    /**
     * https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java
     * @return
     * @throws IOException
     */
    private File openDirectoryChooser() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("export directory selection");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        //
        File output = null;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            System.out.println("getCurrentDirectory(): "
//                    +  chooser.getCurrentDirectory());
//            System.out.println("getSelectedFile() : "
//                    +  chooser.getSelectedFile());
            output = chooser.getSelectedFile();
        }
        else {
            throw new IOException("user aborted export dir selection process");
        }
        return output;
    }

    private Formating formatSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                TerminalLauncher.DELIMITER_MAIN
                        + "format:\n"
                        + "  * 1: pdf (WIP)\n"
                        + "  * 2: html\n"
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

    /**
     * Returns the available decknames in the users anki repository.
     * @return
     * @throws IOException
     */
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

    /**
     * Takes a list of strings and lets the user select one of those
     * @param decknames list of deck names
     * @return one name of the list
     */
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
