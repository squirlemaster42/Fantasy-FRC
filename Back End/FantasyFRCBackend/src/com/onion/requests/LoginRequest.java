package com.onion.requests;

import com.onion.responses.LoginConfirmationResponse;

public class LoginRequest extends Request{

    public LoginRequest() {
        super("Login");
    }

    //TODO Handle input
    @Override
    public String handleRequest(final String input) {
        String user = "NATE";
        String pass = "MARKS";
        if(input.split(",")[0].equals(user) && input.split(",")[1].equals(pass)){
            LoginConfirmationResponse r = new LoginConfirmationResponse();
            return r.getResponse();
        }
        return "You Failed";
    }
}
