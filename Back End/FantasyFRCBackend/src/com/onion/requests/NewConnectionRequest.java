package com.onion.requests;

import com.onion.client.Client;
import com.onion.client.ClientHandler;

public class NewConnectionRequest extends Request {

    private final Client c;

    public NewConnectionRequest(final String ip){
        super("Request New Connection"); //TODO Underscores instead of spaces?
        c = new Client(ip);
    }

    public String handleRequest(String ip){
        ClientHandler.getInstance().registerClient(c);
        return "Oh man we did it";
    }

}
