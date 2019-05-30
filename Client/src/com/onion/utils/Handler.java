package com.onion.utils;

import com.onion.client.Client;
import javafx.stage.Stage;

public class Handler {
    private static Handler ourInstance = new Handler();

    public static Handler getInstance() {
        return ourInstance;
    }

    private Stage primaryStage;
    private Client server;

    private Handler() {}

    public void setPrimaryStage(final Stage primaryStage){
        if(this.primaryStage == null){
            this.primaryStage = primaryStage;
        }
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    public void setServer(final Client server){
        if(this.server == null){
            this.server = server;
        }
    }

    public Client getServer(){
        return this.server;
    }
}
