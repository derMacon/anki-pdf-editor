package com.dermacon.data.worker.multithreading;

import com.dermacon.data.project.ProjectInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PDFMainTest {

    public static void main(String[] args) throws IOException {
        String path = new File(System.getProperty("user.dir")).getParent() + "/lastDocs/pdf/CVL.pdf";
        File f = new File(path);
        PDDocument pdf = PDDocument.load(f);

        ProjectInfo.InfoBuilder b = new ProjectInfo.InfoBuilder();
        b.currPage("1");
        b.deckName("TestDeck");
        b.pdfName("CVL.pdf");


        Renderer r = new Manager(b.build());
        r.renderPageSurrounding(1);


    }

}
