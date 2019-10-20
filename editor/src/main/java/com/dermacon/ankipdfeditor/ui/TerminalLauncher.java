package com.dermacon.ankipdfeditor.ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Terminal Launcher of the ui. Delegates the appropriate queries to
 * the ui respective elements. Needed to abstract the ui to make it
 * possable to implement another gui version.
 */
public class TerminalLauncher implements Runnable {

    public static final String DELIMITER_MAIN = "==========================\n";
    public static final String DELIMITER_SEC =  "  ------------------------\n";

    private static final String UPDATE_PROJECT_QUERY =
            DELIMITER_MAIN
                    +"current project:\n%s"
                    + DELIMITER_MAIN
                    + "type\n"
                    + "  - e to edit project properties\n"
                    + "  - a to write new cards (pushes to anki\n"
                    + "      api and exports html automatically)\n"
                    + "  - x to export a specified deck\n"
                    + "  - w to push to anki connect\n"
                    + "  - q to quit without pushing\n"
                    + "  - wq to push and exit\n"
                    + DELIMITER_SEC
                    + "input: ";

    @Override
    public void run() {
        boolean keepRunning;
        System.out.println("Anki-Editor - version 1.1\n");

        try {

            UserInterface ui = new TerminalUI();
            do {
                keepRunning = runQuery(ui);
            } while (keepRunning);

        } catch (IOException e) {
            System.out.println(DELIMITER_MAIN + e.getMessage());
//            e.printStackTrace();
        }

        System.out.println(DELIMITER_MAIN + "shutdown");
        System.exit(0);
    }

    private boolean runQuery(UserInterface ui) throws IOException {
        boolean keepRunning;

        String greetings = String.format(UPDATE_PROJECT_QUERY, ui.getProjectController().getProjectInfo().toFormattedString());
        System.out.print(greetings);
        String choice = new Scanner(System.in).nextLine().toLowerCase();

        if (!choice.matches("(e|w|a|q|wq|x)")) {
            throw new IOException("invalid user input: " + choice);
        }

        if (choice.equals("a")) {
            ui.openEditor();
            ui.openPdfViewer();
            ui.exportCurrStack();
            ui.pushToAnki();
        }

        if (choice.equals("e")) {
            ui.updateProjectInfo();
        }

        if (choice.matches("wq|w")) {
            ui.pushToAnki();
        }

        if (choice.contains("q")) {
            ui.saveProjHistory();
        }

        if (choice.equals("x")) {
            ui.exportAnyStack();
        }

        // a shutdown is can only be prevented if the user
        // wants to edit the project info OR wants to
        // save his work
        return choice.matches("(e|w)");
    }

}
