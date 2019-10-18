package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;

public class NameLstReply extends AnkiReply {

    private final String[] result;

    public NameLstReply(String[] result, String error) {
        super(error);
        this.result = result;
    }

    public String[] getResult() {
        return this.result;
    }

}
