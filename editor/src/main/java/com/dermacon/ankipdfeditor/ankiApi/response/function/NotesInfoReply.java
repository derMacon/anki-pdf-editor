package com.dermacon.ankipdfeditor.ankiApi.response.function;

import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.AnkiConnectWrapper;
import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;
import com.dermacon.ankipdfeditor.ankiApi.ankiCardModel.AnkiConnectCardModel;

import java.util.ArrayList;

public class NotesInfoReply extends AnkiReply {

    private ArrayList<AnkiConnectCardModel> result;

    // todo
    public NotesInfoReply(ArrayList<AnkiConnectCardModel> result, String error) {
        super(error);
        this.result = result;
    }

    public ArrayList<AnkiConnectCardModel> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "res: " + result;
    }
}
