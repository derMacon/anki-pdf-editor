package com.dermacon.ankipdfeditor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public FXMLController() {
    }

    public void setImgVwResponsive(Stage stage) {
        imgVw_page.fitWidthProperty().bind(stage.widthProperty());
    }

    public int turnNextPage() {
        System.out.println("next");
        return 42;
    }

    public int turnPrevPage() {
        System.out.println("prev");
        return 41;
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

//            imgVw_page.setImage(projectController.getCurrPageImage());
//
//            lbl.setText(String.valueOf(projectController.getProjectInfo().getCurrPage()));
        });
    }





}


