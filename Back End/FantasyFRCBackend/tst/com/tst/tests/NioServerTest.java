package com.tst.tests;

import com.onion.requests.LoginRequest;
import com.onion.requests.NewConnectionRequest;
import com.onion.requests.RequestList;
import com.onion.server.NIOServer;

public class NioServerTest {

    public static void main(String[] args){
        RequestList.getInstance().addRequest(LoginRequest.class);
        RequestList.getInstance().addRequest(NewConnectionRequest.class);

        NIOServer server = new NIOServer( "10.12.68.20", 19672);
        server.startThread();
    }


}
