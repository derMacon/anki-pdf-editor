package com.silas.ankiapiconnector.logic;

import java.io.File;

public interface Document {
    void turnNextPage();
    void turnPrevPage();

    Integer getCurrentPageNum();
    String getNextPage_url();
    String getPrevPage_url();
    Integer getPageCount();
}
