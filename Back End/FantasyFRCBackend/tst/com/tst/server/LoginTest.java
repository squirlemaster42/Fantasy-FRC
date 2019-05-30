package com.tst.server;

import com.onion.requests.LoginRequest;
import com.onion.requests.RequestList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTest {

    @Test
    void testLogin(){
        RequestList.getInstance().addRequest(LoginRequest.class);

        try {
            assertEquals("LoginRequest:", RequestList.getInstance().getRequest("LoginRequest:").newInstance().getRequestID());
            assertEquals("LoginConfirmationResponse: true", RequestList.getInstance().getRequest("LoginRequest:").newInstance().handleRequest("LoginRequest: Nate natepass".split(" ")));
            assertEquals("LoginConfirmationResponse: false", RequestList.getInstance().getRequest("LoginRequest:").newInstance().handleRequest("LoginRequest: Nate dumbo".split(" ")));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
