package com.onion.requests;

import com.onion.responses.LoginConfirmationResponse;
import com.onion.sql.SQLClient;

public class LoginRequest extends Request{

    public LoginRequest() {
        super("LoginRequest:");
    }

    @Override
    public String handleRequest(final String[] input) {
        System.out.println("Handling Login Request");

        final String inputUser = input[1];
        final String inputPass = input[2];

        final String sqlPass = SQLClient.getInstance().readUser(inputUser);

        LoginConfirmationResponse response;

        if(inputPass.equals(sqlPass)){
            response = LoginConfirmationResponse.createResponse(true);
        }else{
            response = LoginConfirmationResponse.createResponse(false);
        }

        return response.getResponse();
    }
}
