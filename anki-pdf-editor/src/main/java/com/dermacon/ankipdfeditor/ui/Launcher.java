package com.dermacon.ankipdfeditor.ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Terminal Launcher of the ui. Delegates the appropriate queries to
 * the ui respective elements. Needed to abstract the ui to make it
 * possable to implement another gui version.
 */
public class Launcher implements Runnable {

    private static final String UPDATE_PROJECT_QUERY =
            "Current project: %s\n"
                    + "Type\n"
                    + "  - a to write new cards\n"
                    + "  - e to edit project properties\n"
                    + "  - wq to save with exit\n"
                    + "  - w to save without exit\n\n";

    private UserInterface ui;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning;

        System.out.println("Anki-Editor - version 1.0\n");

        do {
            System.out.println(String.format(UPDATE_PROJECT_QUERY, ui.displayProjectInfo()));
//        shutdown = runQuery("w");
            keepRunning = runQuery(scanner.nextLine().toLowerCase());
        } while (keepRunning);

        System.out.println("shutdown");
        System.exit(0);
    }

    private boolean runQuery(String choice) {
        boolean keepRunning;

        try {

            if (choice.equals("a")) {
                ui.openEditor();
            } else if (choice.equals("e")) {
                ui.updateProjectInfo();
            } else if (choice.equals("wq")) {
                ui.save();
            } else if (choice.equals("q")) {
                ui.save();
                keepRunning = false;
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
