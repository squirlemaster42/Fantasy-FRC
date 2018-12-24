package com.onion.client;

import com.onion.utils.ClientUtils;

public class Client{

    private final int id;
    private boolean connected;

    public Client(final String ip){
        this.id = ClientUtils.generateClientID(ip);
        connected = true;
    }

    public int getId(){
        return this.id;
    }
}
