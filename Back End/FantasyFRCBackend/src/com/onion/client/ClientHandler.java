package com.onion.client;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler{

    private static ClientHandler instance;

    public static ClientHandler getInstance(){
        if(instance == null){
            instance = new ClientHandler();
        }
        return instance;
    }

    private final Map<Integer, Client> clientMap;

    public ClientHandler(){
        this.clientMap = new HashMap<>();
    }

    public void addClient(final Client client){
        clientMap.put(client.getId(), client);
    }

    public void removeClient(final int clientID){
        clientMap.remove(clientID);
    }

    public void removeClient(final Client client){
        clientMap.remove(client.getId(), client);
    }

    public Client getClient(final int id){
        if(clientMap.containsKey(id)){
            return clientMap.get(id);
        }
        return null;
    }
}
