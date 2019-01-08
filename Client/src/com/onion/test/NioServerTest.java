package com.onion.test;

import com.onion.client.Client;

import java.io.IOException;

public class NioServerTest {
    public static void main(String[] args){
        Client c = new Client("10.12.67.234", 19672);
        c.start();
        try {
            c.getClient().write("Hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
