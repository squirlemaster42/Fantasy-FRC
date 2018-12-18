package com.onion.main;

import com.onion.requests.Server;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        File f = new File("mytestkey.jks");
        f.delete();

        try {
            Server server = new Server(19965);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
