package com.onion.dataRequests;

public class ScoreRequest implements Request{
    @Override
    public String makeRequest(String teamNumber, int clientID) {
        return "ScoreRequest: " + teamNumber;
    }
}
