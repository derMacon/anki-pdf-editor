package com.silas.ankiapiconnector.logic;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PdfDocTest {

    private static final String URL_TESTRES = "/src/main/resources/testResources/";
    private static final String URL_RESTEMPPAGES = "http://localhost:8080/tempPages/pdf/%s.pdf";
    private static final String URL = System.getProperty("user.dir") + URL_TESTRES + "testDoc_5pages.pdf";
    private static final String OUT_OF_BOUND = "outOfBound";

    @Test
    public void test_getURL_init() throws IOException {
        Document doc = new PdfDoc(URL);
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, OUT_OF_BOUND), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 1), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getNextPage_url());
    }

    @Test
    public void test_getURL_turnNextPage() throws IOException {
        Document doc = new PdfDoc(URL);

        doc.turnNextPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 1), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 3), doc.getNextPage_url());

        doc.turnNextPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 3), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 4), doc.getNextPage_url());

        doc.turnNextPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 3), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 4), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 5), doc.getNextPage_url());

        doc.turnNextPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 4), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 5), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, OUT_OF_BOUND), doc.getNextPage_url());

        doc.turnNextPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 4), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 5), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, OUT_OF_BOUND), doc.getNextPage_url());
    }

    @Test
    public void test_getURL_turnPrevPage() throws IOException {
        Document doc = new PdfDoc(URL);

        doc.turnPrevPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, OUT_OF_BOUND), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 1), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getNextPage_url());

        doc.turnNextPage();
        doc.turnPrevPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, OUT_OF_BOUND), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 1), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getNextPage_url());

        doc.turnNextPage();
        doc.turnNextPage();
        doc.turnPrevPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 1), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 2), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 3), doc.getNextPage_url());

        doc.turnNextPage();
        doc.turnNextPage();
        doc.turnNextPage();
        doc.turnPrevPage();
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 3), doc.getPrevPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 4), doc.getCurrPage_url());
        Assert.assertEquals(String.format(URL_RESTEMPPAGES, 5), doc.getNextPage_url());
    }

}