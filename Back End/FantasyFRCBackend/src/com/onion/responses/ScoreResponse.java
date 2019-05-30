package com.onion.responses;

import com.onion.match.Scorer;

public class ScoreResponse implements Response {

    private ResponseType responseType = ResponseType.SCORERESPONSE;

    public ScoreResponse(){

    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String getResponse() {
        return null;
    }
}
