package com.onion.responses;

public class LoginConfirmationResponse implements Response {

    private final ResponseType responseType = ResponseType.LOGINCONFIRMATIONRESPONSE;

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String getResponse() {
        return "Yo you gucci";
    }
}
