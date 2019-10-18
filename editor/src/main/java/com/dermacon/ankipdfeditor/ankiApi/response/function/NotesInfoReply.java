package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;

public class NotesInfoReply extends AnkiReply {

    private Object result;

    // todo
    public NotesInfoReply(Object result, String error) {
        super(error);
    }

    public Object getResult() {
        return result;
    }
}
