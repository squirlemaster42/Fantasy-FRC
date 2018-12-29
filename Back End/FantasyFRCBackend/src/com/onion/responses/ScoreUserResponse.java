package com.onion.responses;

public class ScoreUserResponse implements Response {

    private final ResponseType responseType = ResponseType.SCOREUSERRESPONSE;

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String getResponse() {
        return null;
    }
}
