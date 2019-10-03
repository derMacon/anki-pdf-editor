package com.dermacon.data.worker.multithreading;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PDFMainTest {

    public static void main(String[] args) throws IOException {
        System.out.println();
        String path = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/pdf/CVL.pdf";
        File f = new File(path);
        PDDocument pdf = PDDocument.load(f);

        Renderer r = new Manager(pdf);
        r.renderPageSurrounding(9);
    }

}
