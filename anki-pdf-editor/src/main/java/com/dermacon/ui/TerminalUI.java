package com.dermacon.ui;


import com.dermacon.GuiLauncher;
import com.dermacon.data.project.AnkiConnector;
import com.dermacon.data.project.ProjectController;

import java.io.IOException;

public class TerminalUI implements UserInterface {

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
//        String pathToDeckFile = projectController.getProjectInfo().getDeckPath();
//        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToDeckFile);
//        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void openPdfViewer() {
        GuiLauncher.launch(projectController);
    }

    @Override
    public void updateProjectInfo() {
        // todo
        System.out.println("todo");
        // controller.setProjectInfo(...);
    }

    @Override
    public void save() throws IOException {
        ankiConnector.pushToAnki(this.projectController.getProjectInfo());
        projectController.saveProjHistory();
    }

    @Override
    public ProjectController displayProjectInfo() {
        return this.projectController;
    }
}
