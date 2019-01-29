package com.onion.requests;

import com.onion.client.Client;
import com.onion.client.ClientHandler;

public class NewConnectionRequest extends Request {

    public NewConnectionRequest(){
        super("ConnectionRequest"); //TODO Underscores instead of spaces?
    }

    public String handleRequest(String[] input){
        Client c = new Client(input[1]);
        ClientHandler.getInstance().registerClient(c);
        return "You good";
    }

}
