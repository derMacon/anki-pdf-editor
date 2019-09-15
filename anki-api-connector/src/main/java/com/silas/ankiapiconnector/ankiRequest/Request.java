package com.silas.ankiapiconnector.ankiRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.silas.ankiapiconnector.ankiRequest.response.Response;

import java.lang.reflect.Type;

public abstract class Request {

    private Object action;
    private Integer version;

    public Request(Object action, Integer version) {
        this.action = action;
        this.version = version;
    }

    public Object getAction() {
        return action;
    }

    public Integer getVersion() {
        return version;
    }

    public void setAction(Object action) {
        this.action = action;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    //    public abstract String toJson();
//
//    protected <T extends Response> Response fromJson(Class<T> responseType, String json) {
//        return new Gson().fromJson(json, responseType);
//    }
}
