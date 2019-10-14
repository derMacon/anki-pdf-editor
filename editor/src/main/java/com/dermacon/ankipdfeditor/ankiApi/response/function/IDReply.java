package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiStatusReply;

public class IDReply extends AnkiStatusReply {

    private Integer result;

    public IDReply(Integer result, String error) {
        super(error);
    }

    public Integer getResult() {
        return result;
    }
}
