package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiStatusReply;

public class NotesInfoReply extends AnkiStatusReply {

    private Object result;

    // todo
    public NotesInfoReply(Object result, String error) {
        super(result, error);
    }

    public Object getResult() {
        return result;
    }
}
