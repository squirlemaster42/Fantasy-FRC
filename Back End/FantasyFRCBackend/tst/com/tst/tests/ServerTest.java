package com.tst.tests;

import com.onion.requests.LoginRequest;
import com.onion.requests.RequestList;
import com.onion.server.Server;

import java.io.File;

public class ServerTest {

    public static void main(String[] args){
        File f = new File("mytestkey.jks");
        File realf = new File(f.getAbsolutePath());
        realf.delete();
        RequestList.getInstance().addRequest(LoginRequest.class);

        Server server = new Server(19965);
        server.start();
    }
}
