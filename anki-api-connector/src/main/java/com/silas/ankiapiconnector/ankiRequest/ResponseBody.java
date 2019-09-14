package com.silas.ankiapiconnector.ankiRequest;

public class ResponseBody<E> {
    private E result;
    private String error;

    public ResponseBody(E result, String error) {
        this.result = result;
        this.error = error;
    }

    public E getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public void setResult(E result) {
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
