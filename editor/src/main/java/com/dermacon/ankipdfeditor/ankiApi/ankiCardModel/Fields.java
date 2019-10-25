package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

/**
 * Anki card model that will be used by the gson lib to parse the given json
 * output from the anki connect addon / api.
 */
public class Fields {
    private String Front;

    public Fields(String front) {
        Front = front;
    }

    public String getFront() {
        return Front;
    }
}
