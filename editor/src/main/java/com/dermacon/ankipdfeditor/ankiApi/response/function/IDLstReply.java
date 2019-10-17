package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiStatusReply;

import java.util.ArrayList;

public class IDLstReply extends AnkiStatusReply {

    private ArrayList<Long> result;
//    private Integer[] result;

    public IDLstReply(ArrayList<Long> result, String error) {
        super(result, error);
    }

    public ArrayList<Long> getResult() {
        return result;
    }
}
