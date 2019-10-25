package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

import java.util.ArrayList;

/**
 * Anki card model that will be used by the gson lib to parse the given json
 * output from the anki connect addon / api.
 */
public class AnkiConnectWrapper {
    private ArrayList<AnkiConnectCardModel> info;

    public AnkiConnectWrapper(ArrayList<AnkiConnectCardModel> info) {
        this.info = info;
    }

    public ArrayList<AnkiConnectCardModel> getInfo() {
        return info;
    }
}
