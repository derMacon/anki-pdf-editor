package com.silas.ankiapiconnector.logic;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfDoc implements Document {

    private static final File OUT_OF_BOUND = new File("outOfBound.pdf");

    private static final String CURR_PAGE_NAME = "currPage.pdf";
    private static final String NEXT_PAGE_NAME = "nextPage.pdf";
    private static final String PREV_PAGE_NAME = "prevPage.pdf";

    private File currPage;
    private File nextPage;
    private File prevPage;

    private List<PDDocument> pages = null;
    private int currPageIdx = 0;
    private int pageCnt;


    public PdfDoc(String path) throws IOException {
        PDDocument doc = PDDocument.load(new File(path));
        Splitter splitter = new Splitter();
        pages = splitter.split(doc);

        this.pageCnt = pages.size();

    }

    @Override
    public void turnNextPage() {
        if (currPageIdx + 1 < pageCnt) {
            currPageIdx++;
        }
    }

    @Override
    public void turnPrevPage() {
        if (currPageIdx >= 0) {
            currPageIdx--;
        }
    }

    @Override
    public Integer getCurrentPageNum() {
        return this.currPageIdx + 1;
    }

    @Override
    public Integer getPageCount() {
        return this.pageCnt;
    }
}
