package com.onion.dataRequests;

public class AccountScoreRequest implements Request{
    @Override
    public String makeRequest(String input, int clientID) {
        return "AccountScoreRequest: " + input;
    }
}
