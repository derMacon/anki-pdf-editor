package com.silas.ankiapiconnector.logic;

import java.io.File;

public class PdfDoc implements Document {
    @Override
    public File getCurrentPage() {
        return null;
    }

    @Override
    public File getNextPage() {
        return null;
    }

    @Override
    public File getPrevPage() {
        return null;
    }

    @Override
    public void turnNextPage() {

    }

    @Override
    public void turnPrevPage() {

    }

    @Override
    public Integer getCurrentPageNum() {
        return null;
    }

    @Override
    public Integer getPageCount() {
        return null;
    }
}
