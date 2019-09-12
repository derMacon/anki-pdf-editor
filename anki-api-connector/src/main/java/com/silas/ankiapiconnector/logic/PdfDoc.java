package com.silas.ankiapiconnector.logic;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;

public class PdfDoc implements Document {



    private static final String URL_RESLASTDOC =  System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/lastDocs/";
    private static final String URL_RES_TEMP_PAGES = System.getProperty("user.dir") + "/src/main/resources/META-INF/resources/tempPages/pdf/";

    private static final String OUT_OF_BOUND = URL_RES_TEMP_PAGES + "outOfBound.pdf";
    private static final String CURR_PAGE = URL_RES_TEMP_PAGES + "currentPage.pdf";
    private static final String NEXT_PAGE = URL_RES_TEMP_PAGES + "nextPage.pdf";
    private static final String PREV_PAGE = URL_RES_TEMP_PAGES + "previousPage.pdf";

    private File currPage;
    private File nextPage;
    private File prevPage;

    private List<PDDocument> pages = null;
    private int currPageIdx = 0;
    private int pageCnt;


    public PdfDoc(String path) throws IOException {
        File file = new File(path);
        assert file != null && file.isFile();
        PDDocument doc = PDDocument.load(file);
        Splitter splitter = new Splitter();
        pages = splitter.split(doc);

        this.pageCnt = pages.size();
//        savePagesInScope();

        Iterator<PDDocument> iterator = pages.listIterator();

        //Saving each page as an individual document
        int i = 1;
        while(iterator.hasNext()) {
            PDDocument pd = iterator.next();
            pd.save(URL_RES_TEMP_PAGES + i++ +".pdf");
        }
        System.out.println("Multiple PDF’s created");
    }


    /**
     * https://www.tutorialspoint.com/pdfbox/pdfbox_splitting_a_pdf_document.htm
     */
    private void savePagesInScope() {
        try {
            savePage(currPageIdx - 1, PREV_PAGE);
            savePage(currPageIdx, CURR_PAGE);
            savePage(currPageIdx + 1, NEXT_PAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePage(int idx, String name) throws IOException {
        if (idx < 0) {
            Files.copy(Paths.get(OUT_OF_BOUND), Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
        } else if (idx >= pageCnt) {
            Files.copy(Paths.get(OUT_OF_BOUND), Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
        } else {
            pages.get(idx).save(name);
        }
    }

    @Override
    public void turnNextPage() {
        if (currPageIdx + 1 < pageCnt) {
            currPageIdx++;
        }
        savePagesInScope();
    }

    @Override
    public void turnPrevPage() {
        if (currPageIdx >= 0) {
            currPageIdx--;
        }
        savePagesInScope();
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
