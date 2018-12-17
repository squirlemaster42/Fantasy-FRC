package com.onion.main;

import com.onion.dataRequests.DataRequest;
import com.onion.states.LoginState;
import com.onion.states.MainScreenState;
import com.onion.states.StateManager;

import com.onion.utils.Handler;
import javafx.application.Application;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataRequest dataRequest = new DataRequest("10.12.67.173", 19965);
        dataRequest.start();

        Handler.getInstance().setPrimaryStage(primaryStage);

        primaryStage.show();

        LoginState loginState = new LoginState();
        loginState.createScene(Handler.getInstance().getPrimaryStage());
        MainScreenState mainScreenState = new MainScreenState();
        mainScreenState.createScene(Handler.getInstance().getPrimaryStage());

        StateManager.getInstance().addState("LoginState", loginState);
        StateManager.getInstance().addState("MainScreenState", mainScreenState);
        try {
            StateManager.getInstance().setCurrentState("LoginState");
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }
}
