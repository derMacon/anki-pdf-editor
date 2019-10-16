package com.dermacon.ankipdfeditor.data.card;

/**
 * Error when the card syntax is invalid
 */
public class IncompleteSyntaxException extends RuntimeException {

    public IncompleteSyntaxException(String message) {
        super(message);
    }

}
