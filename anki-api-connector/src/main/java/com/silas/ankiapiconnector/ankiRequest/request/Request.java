package com.silas.ankiapiconnector.ankiRequest.request;

public class Request {
    private String action;
    private Integer version;

    public Request(String action, Integer version) {
        this.action = action;
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public Integer getVersion() {
        return version;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
