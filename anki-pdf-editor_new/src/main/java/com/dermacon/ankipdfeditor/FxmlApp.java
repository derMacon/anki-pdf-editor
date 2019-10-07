package com.dermacon.ankipdfeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * JavaFX App
 */
public class FxmlApp extends Application {

    private static final String FXML_NAME = "primary";
//    private static ProjectController projectController;

    public void launchPdf() {
//        projectController = projectController_;
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlApp.class.getResource(FXML_NAME + ".fxml"));
        fxmlLoader.setController(new FXMLController());
        Parent parent = fxmlLoader.load();

        FXMLController controller = fxmlLoader.getController();
//        controller.setProjectController(this.projectController);
        SpringApiController.setJFXController(controller);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        controller.setImgVwResponsive(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println("exit");
    }

}