package com.dermacon.ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Terminal Launcher of the ui. Delegates the appropriate queries to
 * the ui respective elements. Needed to abstract the ui to make it
 * possable to implement another gui version.
 */
public class TerminalLauncher implements Runnable {

    private static final String UPDATE_PROJECT_QUERY =
            "Current project: %s\n"
                    + "Type\n"
                    + "  - a to write new cards\n"
                    + "  - e to edit project properties\n"
                    + "  - wq to save with exit\n"
                    + "  - w to save without exit\n\n";

    @Override
    public void run() {
        boolean keepRunning;
        System.out.println("Anki-Editor - version 1.0\n");

        do {
            keepRunning = runQuery();
        } while (keepRunning);

        System.out.println("shutdown");
        System.exit(0);
    }

    private boolean runQuery() {
        boolean keepRunning;

        try {

            UserInterface ui = new TerminalUI();

            System.out.println(String.format(UPDATE_PROJECT_QUERY, ui.displayProjectInfo()));
            String choice = new Scanner(System.in).nextLine().toLowerCase();

            if (choice.equals("a")) {
                ui.openEditor();
                ui.openPdfViewer();
            } else if (choice.equals("e")) {
                ui.updateProjectInfo();
            } else if (choice.equals("wq")) {
                ui.save();
            } else if (choice.equals("w")) {
                ui.save();
            }

            keepRunning = choice.matches("(e|w|a)");

        } catch (IOException e) {
            e.printStackTrace();
            keepRunning = false;
        }

        // a shutdown is can only be prevented if the user
        // wants to edit the project info OR wants to
        // save his work
        return keepRunning;
    }


}
