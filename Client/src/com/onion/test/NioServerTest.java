package com.onion.test;

import com.onion.client.Client;

import java.net.InetAddress;

public class NioServerTest {
    public static void main(String[] args){
        Client c = new Client("10.12.68.20", 19672);
        c.start();
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            Thread.sleep(500);
            c.getClient().write("ConnectionRequest " + inetAddress.getHostAddress());
            c.getClient().read();
            c.getClient().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.stop();
    }
}
