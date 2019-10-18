package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;

public class IDReply extends AnkiReply {

    private Integer result;

    public IDReply(Integer result, String error) {
        super(error);
    }

    public Integer getResult() {
        return result;
    }
}
