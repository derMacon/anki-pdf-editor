package com.dermacon;

import com.dermacon.ui.PrimaryController;
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

    private static Scene scene;
    private static ProjectInfo projectInfo;

    public void launchPdf(ProjectInfo projectInfo_) {
        projectInfo = projectInfo_;
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        String fxml = "primary";
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlApp.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();

        PrimaryController controller = fxmlLoader.getController();
        controller.setProjectInfo(this.projectInfo);
        SpringApi.setJFXController(controller);

        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        System.out.println("exit");
    }

}