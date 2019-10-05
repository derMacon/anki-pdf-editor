package com.dermacon.ankiApi.request;

import java.lang.reflect.Type;

/**
 * Transmittable anki request.
 */
public abstract class AnkiRequest {

    /**
     * Default anki connect version.
     */
    private static final Integer DEFAULT_VERSION = 6;

    /**
     * Current anki connect version.
     */
    protected Integer version;

    /**
     * Default constructor.
     */
    public AnkiRequest() {
        this.version = DEFAULT_VERSION;
    }

    /**
     * Generate json representation to transmit/push to the anki api.
     * @return json representation
     */
    public abstract String toJson();

    /**
     * Returns the response type of the request.
     * @return the response type of the request
     */
    public abstract Type getResponseType();

}
