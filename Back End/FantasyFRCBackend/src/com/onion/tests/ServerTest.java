package com.onion.tests;

import com.onion.requests.Server;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args){
        try {
            Server server = new Server(19965);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
