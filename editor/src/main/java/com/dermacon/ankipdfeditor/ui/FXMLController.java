package com.dermacon.ankipdfeditor.ui;

import com.dermacon.ankipdfeditor.data.project.ProjectController;
import com.dermacon.ankipdfeditor.springApi.SpringApiController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    @FXML
    private BorderPane brdPn_container;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgVw_page;

    private ProjectController projectController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl.setAlignment(Pos.CENTER);
        lbl.setFont(new Font("Cambria", 25));
        SpringApiController.setJFXController(this);
        updateGui();
    }

    public void setImgVwResponsive(Stage stage) {
        imgVw_page.fitWidthProperty().bind(stage.widthProperty());
    }

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
        lbl.setText(projectController.toString());
    }

    public int getCurrPage() {
        return this.projectController.getProjectInfo().getCurrPage();
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


