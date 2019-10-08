package com.dermacon.ankipdfeditor.data.worker.multithreading;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PDFMainTest {

    public static void main(String[] args) throws IOException {
        String path = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/pdf/CVL.pdf";
        File f = new File(path);
        PDDocument pdf = PDDocument.load(f);


//        Assignments a = new Assignments(20);
//        a.addPage(1);
//        System.out.println(a);
//        a.addPage(2);
//        System.out.println(a);

    }

}
