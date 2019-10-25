package com.dermacon.ankipdfeditor.ankiApi.request.function;

public class NotesInfoRequest extends FunctionalRequest {

    private static final String JSON_TEMPLATE = "{\n"
            + "\"action\": \"notesInfo\",\n"
            + "\"version\": %s,\n"
            + "\"params\": {\"notes\": [%s]}"
            + "}";

    private final long nodeId;

    public NotesInfoRequest(Long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toJson() {
        return String.format(JSON_TEMPLATE, this.version, this.nodeId);
    }

}
