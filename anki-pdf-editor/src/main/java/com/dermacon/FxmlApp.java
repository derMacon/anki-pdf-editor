package com.dermacon;

import com.dermacon.apiController.SpringApiController;
import com.dermacon.logic.ProjectInfo;
import com.dermacon.ui.FXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class FxmlApp extends Application {

    private static final String FXML_NAME = "primary";
    private static ProjectInfo projectInfo;

    public void launchPdf(ProjectInfo projectInfo_) {
        projectInfo = projectInfo_;
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlApp.class.getResource(FXML_NAME + ".fxml"));
        Parent parent = fxmlLoader.load();

        FXMLController controller = fxmlLoader.getController();
        controller.setProjectData(this.projectInfo);
        SpringApiController.setJFXController(controller);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println("exit");
    }

}