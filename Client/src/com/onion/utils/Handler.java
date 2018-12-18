package com.onion.utils;

import com.onion.dataRequests.DataRequest;
import javafx.stage.Stage;

public class Handler {
    private static Handler ourInstance = new Handler();

    public static Handler getInstance() {
        return ourInstance;
    }

    private Stage primaryStage;
    private DataRequest server;

    private Handler() {}

    public void setPrimaryStage(final Stage primaryStage){
        if(this.primaryStage == null){
            this.primaryStage = primaryStage;
        }
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    public void setServer(final DataRequest server){
        if(this.server == null){
            this.server = server;
        }
    }

    public DataRequest getServer(){
        return this.server;
    }
}
