package com.dermacon.ankipdfeditor.ankiApi.request.consumer;


import com.dermacon.ankipdfeditor.ankiApi.request.AnkiRequest;

public class SyncAnkiRequest extends ConsumingRequest {

    private static final String JSON_TEMPLATE = "{\n" +
            "\"action\": \"sync\",\n" +
            "\"version\": %s\n" +
            "}";

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE, this.version);
    }

}
