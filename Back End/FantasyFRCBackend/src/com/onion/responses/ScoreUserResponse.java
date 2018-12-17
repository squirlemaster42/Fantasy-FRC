package com.onion.responses;

import com.onion.requests.Response;
import com.onion.requests.ResponseType;

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
