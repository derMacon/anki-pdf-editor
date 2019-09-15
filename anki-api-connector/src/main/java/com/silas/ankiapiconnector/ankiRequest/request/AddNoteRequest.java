package com.silas.ankiapiconnector.ankiRequest.request;

import com.silas.ankiapiconnector.logic.Card;

public class AddNoteRequest extends Request {

    private Object params;

    public AddNoteRequest(String action, Integer version, Object params) {
        super(action, version);
        this.params = params;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }
}
