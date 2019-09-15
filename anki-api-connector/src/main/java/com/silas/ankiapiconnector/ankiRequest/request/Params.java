package com.silas.ankiapiconnector.ankiRequest.request;

public class Params {
    private Object note;

    public Params(Object note) {
        this.note = note;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }
}
