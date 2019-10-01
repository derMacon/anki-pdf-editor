package com.dermacon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApiLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ApiLauncher.class, args);
    }

    public static void loadSpringController(String[] args) {
        // use of builder necessary otherwise awt.headlessexception when opening filechooser
        // https://stackoverflow.com/questions/51004447/spring-boot-java-awt-headlessexception
        SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringApi.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

}
