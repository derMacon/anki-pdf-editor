package com.dermacon.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.dermacon.ProjectInfo;
import com.dermacon.SpringApi;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {

    private ProjectInfo projectInfo;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgVw_page;

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
        lbl.setText(projectInfo.toString());
        System.out.println(projectInfo);
    }

    public void turnNextPage() {
        Platform.runLater(() -> {
            lbl.setText("next");
            imgVw_page.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/d/d9/Test.png"));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpringApi.setJFXController(this);
    }
}


