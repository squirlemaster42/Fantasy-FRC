package com.onion.responses;

import com.onion.requests.Response;
import com.onion.requests.ResponseType;

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
