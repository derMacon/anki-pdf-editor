package com.dermacon.ui;


import com.dermacon.GuiLauncher;
import com.dermacon.logic.ProjectController;
import com.dermacon.logic.ProjectInfo;

import java.io.IOException;

public class TerminalUI implements UserInterface {

    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";

    private ProjectController controller;

    public TerminalUI() throws IOException {
        this.controller = new ProjectController();
    }

    @Override
    public void openEditor() throws IOException {
        // open vim
        String pathToDeckFile = controller.getProjectInfo().getDeckFile();
        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToDeckFile);
        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void openPdfViewer() throws IOException {
        GuiLauncher.main(new String[0]);
    }

    @Override
    public void updateProjectInfo() {
        // todo
        System.out.println("todo");
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
