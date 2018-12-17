package com.onion.responses;

import com.onion.requests.Response;
import com.onion.requests.ResponseType;

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
