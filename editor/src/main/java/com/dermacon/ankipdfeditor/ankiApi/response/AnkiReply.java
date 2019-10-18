package com.dermacon.ankipdfeditor.ankiApi.response;

public abstract class AnkiReply {
    private String error;

    public AnkiReply(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "error: " + this.error;
    }
}
