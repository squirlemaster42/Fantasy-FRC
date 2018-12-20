package com.onion.main;

import com.onion.requests.Server;

import java.io.File;

public class Main {

    //TODO Change from sending Strings to objects
    //TODO Give clients IDs

    public static void main(String[] args){
        File f = new File("mytestkey.jks");
        f.delete();

        Server server = new Server(19965);
        server.start();
    }
}
