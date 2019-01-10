package com.onion.test;

import com.onion.client.Client;

public class NioServerTest {
    public static void main(String[] args){
        Client c = new Client("10.12.68.20", 19672);
        c.start();
        try {
            Thread.sleep(500);
            c.getClient().write("Hi");
            c.getClient().read();
            c.getClient().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.stop();
    }
}
