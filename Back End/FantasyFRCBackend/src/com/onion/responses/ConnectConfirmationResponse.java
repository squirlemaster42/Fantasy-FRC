package com.onion.responses;

import com.onion.requests.RequestType;

import java.net.Authenticator;

public class ConnectConfirmationResponse implements Response {
    @Override
    public ResponseType getResponseType() {
        return ResponseType.CONNECTCONFIRMATIONRESPONSE;
    }

    @Override
    public String getResponse() {
        return "Welcome";
    }
}
