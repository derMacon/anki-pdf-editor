package com.dermacon.ankipdfeditor.ui;

import com.dermacon.ankipdfeditor.ankiRequest.PostConnector;
import com.dermacon.ankipdfeditor.apiController.ProjectInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TerminalUI implements UserInterface {

    private static final String NEW_TERMINAL_COMMAND = "gnome-terminal -- vim %s";

    private ProjectController controller;

    public TerminalUI() {
        this.controller = new ProjectController();
    }

    @Override
    public void openEditor() throws IOException {
        // open vim
//        String pathToDeckFile = controller.getProjectInfo().getDeckFile();
//        String openNewTerminalCommand = String.format(NEW_TERMINAL_COMMAND, pathToDeckFile);
//        Runtime.getRuntime().exec(openNewTerminalCommand);
    }

    @Override
    public void openPdfViewer() throws IOException {

        FXMLLauncher.main(new String[0]);

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("PdfWindow.fxml"));
//        loader.load();
//
////        FXMLDocumentController gameController = loader.getController();
//
//        Parent root = loader.getRoot();
//        Stage mainStage = new Stage();
//        mainStage.setTitle("Pdf-Viewer");
//        Scene scene = new Scene(root, 1200, 900);
//        mainStage.setScene(scene);
//
//        // Optimal size: 1202 x 932
//        mainStage.setMinWidth(1128);
//        mainStage.setMinHeight(877);
//        mainStage.setMaxWidth(1259);
//        mainStage.setMaxHeight(1028);
//
//        mainStage.setWidth(1202);
//        mainStage.setHeight(932);
//        mainStage.show();
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
