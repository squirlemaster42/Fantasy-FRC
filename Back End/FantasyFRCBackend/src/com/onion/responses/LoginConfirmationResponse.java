package com.onion.responses;

public class LoginConfirmationResponse implements Response {

    @Override
    public ResponseType getResponseType() {
        return ResponseType.LOGINCONFIRMATIONRESPONSE;
    }

    @Override
    public String getResponse() {
        return "Yo you gucci";
    }
}
