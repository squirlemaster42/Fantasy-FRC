package com.onion.responses;

public class LoginConfirmationResponse implements Response {

    public static LoginConfirmationResponse createResponse(final boolean success){
        return new LoginConfirmationResponse(success);
    }

    private final boolean success;

    private LoginConfirmationResponse(final boolean success){
        this.success = success;
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.LOGINCONFIRMATIONRESPONSE;
    }

    @Override
    public String getResponse() {
        return "LoginConfirmationResponse: " + success;
    }
}
