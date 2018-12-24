package com.onion.client;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler{

    private final Map<Integer, Client> clientMap;

    public ClientHandler(final HashMap<Integer, Client> clientMap){
        this.clientMap = clientMap;
    }
}
