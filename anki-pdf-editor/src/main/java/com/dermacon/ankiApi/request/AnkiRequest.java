package com.dermacon.ankiApi.request;

import java.lang.reflect.Type;

// todo rename to AnkiPostRequest
public abstract class AnkiRequest {

    private static final Integer DEFAULT_VERSION = 6;

    protected Integer version;

    public AnkiRequest() {
        this.version = DEFAULT_VERSION;
    }

    public abstract String toJson();

    public abstract Type getResponseType();

}
