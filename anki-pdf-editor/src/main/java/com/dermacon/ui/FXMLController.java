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

public class FXMLController implements Initializable {

    private ProjectController projectController;

    @FXML
    private Label lbl;

    @FXML
    private ImageView imgVw_page;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpringApiController.setJFXController(this);
        updateGui();
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
            String path = projectController.getCurrPageImage();

            // todo img not showing if rendering is too slow
//            while(!(new File(path).exists())) {
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("waiting for img");
//            }

            Image img = new Image("file:" + path);
            imgVw_page.setImage(img);

            lbl.setText(String.valueOf(projectController.getProjectInfo().getCurrPage()));
        });
    }





}


