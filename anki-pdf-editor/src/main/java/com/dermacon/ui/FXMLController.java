package com.dermacon.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.dermacon.data.project.ProjectController;
import com.dermacon.springApi.SpringApiController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private BorderPane brdPn_container;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgVw_page;

    private ProjectController projectController;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpringApiController.setJFXController(this);
        updateGui();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        imgVw_page.fitWidthProperty().bind(stage.widthProperty());
    }

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
        lbl.setText(projectController.toString());
        System.out.println(projectController);
    }

    public int turnNextPage() {
        int newPageNum = projectController.turnNextPage();
        updateGui();
        return newPageNum;
    }

    public int turnPrevPage() {
        int newPageNum = projectController.turnPrevPage();
        updateGui();
        return newPageNum;
    }

    private void updateGui() {
        Platform.runLater(() -> {

            // todo img not showing if rendering is too slow
//            while(!(new File(path).exists())) {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("waiting for img");
//            }

            imgVw_page.setImage(projectController.getCurrPageImage());

            lbl.setText(String.valueOf(projectController.getProjectInfo().getCurrPage()));
        });
    }





}


