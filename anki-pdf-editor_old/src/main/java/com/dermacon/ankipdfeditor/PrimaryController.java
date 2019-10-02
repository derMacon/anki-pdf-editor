package com.dermacon.ankipdfeditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrimaryController {

    private String projectInfo;

    @FXML
    private Label lbl;

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
        lbl.setText(projectInfo.toString());
        System.out.println(projectInfo);
    }
}


