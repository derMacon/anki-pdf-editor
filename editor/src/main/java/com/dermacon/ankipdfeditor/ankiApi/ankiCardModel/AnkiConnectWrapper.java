package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

import java.util.ArrayList;

public class AnkiConnectWrapper {
    private ArrayList<AnkiConnectCardModel> info;

    public AnkiConnectWrapper(ArrayList<AnkiConnectCardModel> info) {
        this.info = info;
    }

    public ArrayList<AnkiConnectCardModel> getInfo() {
        return info;
    }
}
