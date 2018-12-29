package com.onion.responses;

public class PickConfirmationResponse implements Response {

    private final ResponseType responseType = ResponseType.PICKCONFIRMATIONRESPONSE;

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String getResponse() {
        return null;
    }
}
