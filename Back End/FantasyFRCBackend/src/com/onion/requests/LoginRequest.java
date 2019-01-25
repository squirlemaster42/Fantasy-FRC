package com.onion.requests;

import com.onion.responses.LoginConfirmationResponse;

public class LoginRequest extends Request{

    public LoginRequest() {
        super("Login");
    }

    //TODO Handle input
    @Override
    public String handleRequest(final String[] input) {
        String user = "NATE";
        String pass = "MARKS";
        if(input[0].equals(user) && input[1].equals(pass)){
            LoginConfirmationResponse r = new LoginConfirmationResponse();
            return r.getResponse();
        }
        return "You Failed";
    }
}
