package com.silas.ankiapiconnector.logic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MainShortcut {

    public static void main(String[] args) throws IOException {
        String url_resLastDoc = "/src/main/resources/META-INF/resources/lastDocs/";
        String url_resTempPages = "/src/main/resources/META-INF/resources/tempPages/pdf/";
        String url = System.getProperty("user.dir") + url_resLastDoc + "CVL.pdf";

////        HtmlParser p = new HtmlParser(url);
////        p.parseImg("<1>");
//
//        String HOME_DIR = System.getProperty("user.home");
////        String ANKI_IMG_PAGES = HOME_DIR + "/.local/share/Anki2/User 1/";
//        String ANKI_IMG_PAGES = HOME_DIR + "/.local/share/Anki2/User 1/collection.media/";
//
//        System.out.println("exist: " + Files.exists(new File(ANKI_IMG_PAGES).toPath()));


        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }



    }

}
