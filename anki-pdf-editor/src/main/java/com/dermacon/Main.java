package com.dermacon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        ApiLauncher.loadSpringController(args);
        SpringApplication.run(ApiLauncher.class, args);
        new FxmlApp().launchPdf(new ProjectInfo());
    }

}
