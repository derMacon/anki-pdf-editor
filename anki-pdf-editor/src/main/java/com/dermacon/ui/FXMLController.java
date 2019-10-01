package com.dermacon.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.dermacon.logic.ProjectInfo;
import com.dermacon.apiController.SpringApiController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLController implements Initializable {

    private ProjectInfo projectData;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgVw_page;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpringApiController.setJFXController(this);
    }

    public void setProjectData(ProjectInfo projectData) {
        this.projectData = projectData;
        lbl.setText(projectData.toString());
        System.out.println(projectData);
    }

    public int turnNextPage() {
        System.out.println("turn next page");
        int newPageNum = projectData.turnNextPage();

        Platform.runLater(() -> {
            imgVw_page.setImage(new Image(projectData.getImgPath()));
        });

        return newPageNum;
    }

    public int turnPrevPage() {
        System.out.println("turn prev page");
        int newPageNum = projectData.turnPrevPage();

        Platform.runLater(() -> {
            imgVw_page.setImage(new Image(projectData.getImgPath()));
        });

        return newPageNum;
    }





}


