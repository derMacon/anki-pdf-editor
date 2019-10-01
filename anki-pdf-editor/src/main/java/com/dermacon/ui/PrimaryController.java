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

public class PrimaryController implements Initializable {

    private ProjectInfo projectInfo;

    @FXML
    private Label lbl;

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
        lbl.setText(projectInfo.toString());
        System.out.println(projectInfo);
    }

    public void turnNextPage() {
        Platform.runLater(() -> {lbl.setText("next");});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpringApi.setJFXController(this);
    }
}


