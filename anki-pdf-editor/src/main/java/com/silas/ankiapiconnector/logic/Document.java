package com.silas.ankiapiconnector.logic;

import java.io.File;

public interface Document {
    void turnNextPage();

    void turnPrevPage();

    Integer getCurrentPageNum();

    String getCurrPage_url();

    String getNextPage_url();

    String getPrevPage_url();

    Integer getPageCount();
}
