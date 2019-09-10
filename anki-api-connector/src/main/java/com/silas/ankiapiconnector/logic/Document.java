package com.silas.ankiapiconnector.logic;

import java.io.File;

public interface Document {
    File getCurrentPage();
    File getNextPage();
    File getPrevPage();

    void turnNextPage();
    void turnPrevPage();

    Integer getCurrentPageNum();
    Integer getPageCount();
}
