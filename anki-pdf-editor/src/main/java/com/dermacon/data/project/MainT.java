package com.dermacon.data.project;

import java.io.File;

public class MainT {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/src/main/resources/com/dermacon/manual.pdf";
        System.out.println(new File(path).exists());
    }

}
