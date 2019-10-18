package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;

import java.util.ArrayList;

public class IDLstReply extends AnkiReply {

    private ArrayList<Long> result;
//    private Integer[] result;

    public IDLstReply(ArrayList<Long> result, String error) {
        super(error);
    }

    public ArrayList<Long> getResult() {
        return result;
    }
}
