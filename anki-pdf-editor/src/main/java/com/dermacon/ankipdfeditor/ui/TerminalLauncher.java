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
            "current project:\n%s"
                    + DELIMITER_MAIN
                    + "type\n"
                    + "  - a to write new cards\n"
                    + "  - e to edit project properties\n"
                    + "  - w to push to anki connect\n"
                    + "  - q to quit without pushing\n"
                    + "  - wq to push and exit\n"
                    + DELIMITER_SEC
                    + "input: ";

    @Override
    public void run() {
        boolean keepRunning;
        System.out.println("Anki-Editor - version 1.0\n");

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

        String greetings = String.format(UPDATE_PROJECT_QUERY, ui.getProjectController().getProjectInfo().toString());
        System.out.print(greetings);
        String choice = new Scanner(System.in).nextLine().toLowerCase();

        if (choice.equals("a")) {
            ui.openEditor();
            ui.openPdfViewer();
        } else if (choice.equals("e")) {
            ui.updateProjectInfo();
        } else if (choice.equals("wq") || choice.equals("w")) { // todo merge regex
            ui.push();
            ui.save();
        }
        if (choice.equals("q")) {
            ui.save();
        }

        // todo
//        throw new IOException("invalid user input: " + choice);

        keepRunning = choice.matches("(e|w|a)");


        // a shutdown is can only be prevented if the user
        // wants to edit the project info OR wants to
        // save his work
        return keepRunning;
    }


}
