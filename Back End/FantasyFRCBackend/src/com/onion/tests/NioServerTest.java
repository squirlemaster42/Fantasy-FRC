package com.onion.tests;

import com.onion.server.NIOServer;

public class NioServerTest {

    public static void main(String[] args){
        NIOServer server = new NIOServer( "10.12.67.234", 19672);
        server.startThread();
    }
}
