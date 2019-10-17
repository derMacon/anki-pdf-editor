package com.dermacon.ankipdfeditor.ankiApi.response;

public abstract class AnkiStatusReply {
    private String error;

    public AnkiStatusReply(Object result, String error) {
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
