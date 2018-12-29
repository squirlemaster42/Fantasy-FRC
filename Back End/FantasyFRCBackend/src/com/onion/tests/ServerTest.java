package com.onion.tests;

import com.onion.server.Server;

public class ServerTest {

    public static void main(String[] args){
        Server server = new Server(19965);
        server.start();
    }
}
