package com.dermacon.data.project;

public class PageData {
    private final int currPage;
    private final int pageCnt;

    public PageData(int currPage, int nextPage) {
        this.currPage = currPage;
        this.pageCnt = nextPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    @Override
    public String toString() {
        return "(" + currPage + "/" + pageCnt + ")";
    }
}
