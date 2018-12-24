package com.onion.client;

import java.util.HashMap;
import java.util.Map;

public class ClientMap {

    private final Map<Integer, Client> clientMap;

    public ClientMap(){
        clientMap = new HashMap<>();
    }

    public void addClient(final Client client){
        clientMap.put(client.getId(), client);
    }

    public Client getClient(final int id){
        return clientMap.get(id);
    }

    public void removeClient(final int id){
        clientMap.remove(id);
    }
}
