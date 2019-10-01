package com.dermacon.ankipdfeditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private ImageView imgVw;
    private String test;

    public void init(String test) {
        this.test = test;
        System.out.println(imgVw == null);
    }

    @Override
    public void start(Stage stage) {
        imgVw = initScene(stage);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        imgVw.setImage(new Image("https://upload.wikimedia.org/wikipedia/commons/a/a2/Wikimedia_Outreach_test_logo.png"));
        System.out.println("finito");
        System.out.println(test);
    }

    private ImageView initScene(Stage stage) {
        String dir = "file:" + System.getProperty("user.dir") + "/src/main/java/com/dermacon/ankipdfeditor/";
        Image img = new Image(dir + "Test.png");

        ImageView iv = new ImageView() ;
        iv.setImage(img);

        StackPane s = new StackPane();
        iv.fitWidthProperty().bind(s.widthProperty());
        iv.fitHeightProperty().bind(s.heightProperty());

        Scene scene = new Scene(new StackPane(iv), 640, 480);
        stage.setScene(scene);
        stage.show();

        System.out.println("hier");
        return iv;
    }

    public ImageView main(String[] args) {
        launch();
        return this.imgVw;
    }

}