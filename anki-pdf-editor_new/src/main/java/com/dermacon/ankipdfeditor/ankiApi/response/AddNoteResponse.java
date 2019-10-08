package com.dermacon.ankipdfeditor.ankiApi.response;

/**
 * Response to an ankiaddnote request.
 */
public class AddNoteResponse extends AnkiResponse {

    /**
     * Constructor setting the id and the error code of the response.
     * @param result newly created card id
     * @param error if unequal to null and error occurred
     */
    public AddNoteResponse(final Long[] result, final String error) {
        super(result, error);
    }
}
