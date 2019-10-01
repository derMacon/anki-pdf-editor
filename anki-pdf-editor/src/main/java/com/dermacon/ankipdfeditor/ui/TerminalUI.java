package com.dermacon.ankipdfeditor.ui;

import com.dermacon.ankipdfeditor.ankiRequest.PostConnector;
import com.dermacon.ankipdfeditor.apiController.ProjectInfo;

import java.io.IOException;

public class TerminalUI implements UserInterface {

    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";

    private ProjectController controller;

    public TerminalUI(ProjectController controller) {
        this.controller = controller;
    }

    @Override
    public void openEditor() throws IOException {
        // open vim
        String pathToDeckFile = controller.getProjectInfo().getDeckFile();
        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToDeckFile);
        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void updateProjectInfo() {
        // todo
        // controller.setProjectInfo(...);
    }

    @Override
    public void save() {
        controller.pushToAnki();
    }

    @Override
    public ProjectInfo displayProjectInfo() {
        return controller.getProjectInfo();
    }
}
