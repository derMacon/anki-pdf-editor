package com.silas.ankiapiconnector.ankiRequest.request;

import java.lang.reflect.Type;

// todo rename to AnkiPostRequest
public abstract class Request {

    private static final Integer DEFAULT_VERSION = 6;

    protected Integer version;

    public Request() {
        this.version = DEFAULT_VERSION;
    }

    public abstract String toJson();

    public abstract Type getResponseType();

}
