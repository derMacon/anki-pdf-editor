package com.dermacon.ankipdfeditor.ankiApi.ankiCardModel;

/**
 * Anki card model that will be used by the gson lib to parse the given json
 * output from the anki connect addon / api.
 */
public class Field {
    private String value;
    private int order;

    public Field(String value, int order) {
        this.value = value;
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "value: " + value
                + "; order: " + order;
    }


}

