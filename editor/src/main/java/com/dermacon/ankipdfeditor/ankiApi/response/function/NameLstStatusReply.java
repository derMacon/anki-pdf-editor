package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiStatusReply;

public class NameLstStatusReply extends AnkiStatusReply {

    private final String[] result;

    public NameLstStatusReply(String[] result, String error) {
        super(result, error);
        this.result = result;
    }

    public String[] getResult() {
        return this.result;
    }

}
