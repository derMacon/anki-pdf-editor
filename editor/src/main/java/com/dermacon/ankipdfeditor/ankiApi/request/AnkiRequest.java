package com.dermacon.ankipdfeditor.ankiApi.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
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


    protected static String removeExtension(String fullFileName) {
        int idx = fullFileName.lastIndexOf('.');
        if (idx > 0) {
            fullFileName = fullFileName.substring(0, idx);
        }
        return fullFileName;

    }


}
