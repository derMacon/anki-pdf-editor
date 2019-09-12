package com.silas.ankiapiconnector.logic;

import java.io.File;
import java.io.IOException;

public class MainShortcut {

    public static void main(String[] args) throws IOException {
        String url_resLastDoc = "/src/main/resources/META-INF/resources/lastDocs/";
        String url_resTempPages = "/src/main/resources/META-INF/resources/tempPages/pdf/";
        String url = System.getProperty("user.dir") + url_resLastDoc + "CVL.pdf";

        Document doc = new PdfDoc(url);





    }

}
