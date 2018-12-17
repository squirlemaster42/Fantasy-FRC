package com.onion.utils;

import javafx.stage.Stage;

public class Handler {
    private static Handler ourInstance = new Handler();

    public static Handler getInstance() {
        return ourInstance;
    }

    private Stage primaryStage;

    private Handler() {}

    public void setPrimaryStage(final Stage primaryStage){
        if(this.primaryStage == null){
            this.primaryStage = primaryStage;
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
