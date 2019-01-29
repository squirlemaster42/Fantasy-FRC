package com.onion.dataRequests;

public class LoginRequest implements Request{

    @Override
    public String makeRequest(final String input, final int clientID) {
        return "Login: " + clientID + " " + input;
    }
}
