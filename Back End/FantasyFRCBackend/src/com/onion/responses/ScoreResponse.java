package com.onion.responses;

public class ScoreResponse implements Response {

    private ResponseType responseType = ResponseType.SCORERESPONSE;

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String getResponse() {
        return null;
    }
}
