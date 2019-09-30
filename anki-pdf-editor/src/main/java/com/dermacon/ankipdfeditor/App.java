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

    @Override
    public void start(Stage stage) {
        ImageView currPage = initScene(stage);

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

        return iv;
    }






    public static void main(String[] args) {
        launch();
    }

}