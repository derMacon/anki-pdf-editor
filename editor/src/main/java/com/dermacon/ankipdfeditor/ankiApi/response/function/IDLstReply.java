package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiStatusReply;

public class IDLstReply extends AnkiStatusReply {

    private Integer[] result;

    public IDLstReply(Integer[] result, String error) {
        super(error);
    }

    public Integer[] getResult() {
        return result;
    }
}
