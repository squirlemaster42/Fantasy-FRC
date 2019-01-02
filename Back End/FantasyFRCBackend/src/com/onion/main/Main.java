package com.onion.main;

import com.onion.requests.LoginRequest;
import com.onion.requests.RequestList;
import com.onion.server.Server;

import java.io.File;

public class Main {

    //TODO Change from sending Strings to objects
    //TODO Give clients IDs
    //TODO Add Requests to main

    public static void main(String[] args){
        File f = new File("mytestkey.jks");
        f.delete();

        RequestList.getInstance().addRequest(LoginRequest.class);


        Server server = new Server(19965);
        server.start();
    }
}
