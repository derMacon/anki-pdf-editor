package com.dermacon.ankipdfeditor.ankiApi.response.consumer;

import com.dermacon.ankipdfeditor.ankiApi.response.AnkiReply;

public class ConsumerReply extends AnkiReply {
    public ConsumerReply(Object result, String error) {
        super(error);
    }
}
