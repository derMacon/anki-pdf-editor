package com.dermacon.ankiApi.response;

public abstract class AnkiResponse {
    private Object result;
    private String error;

    public AnkiResponse(Object result, String error) {
        this.result = result;
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Response body:\n" +
                "result: " + this.result.toString() + "\n" +
                "error: " + this.error;
    }
}
