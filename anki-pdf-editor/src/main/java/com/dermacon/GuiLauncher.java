package com.dermacon;

import com.dermacon.data.project.ProjectController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class GuiLauncher {
//    public static void main(String[] args) throws IOException {
//        SpringApplication.run(GuiLauncher.class, args);
//        new FxmlApp().launchPdf(new ProjectController());
//    }

    public static void launch(ProjectController controller) {
        SpringApplication.run(GuiLauncher.class, new String[0]);
        new FxmlApp().launchPdf(controller);
    }
}
