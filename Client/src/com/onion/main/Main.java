package com.onion.main;

import com.onion.client.Client;
import com.onion.states.DraftScreenState;
import com.onion.states.LoginState;
import com.onion.states.MainScreenState;
import com.onion.states.StateManager;

import com.onion.utils.Handler;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Main extends Application {

    private static final String serverIP = "10.12.69.39";
    private static final int serverPort = 19965;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Client dataRequest = new Client(serverIP, serverPort);
        dataRequest.start();

        Image image = new Image("com/onion/states/Logo.png");
        primaryStage.getIcons().add(image);

        Handler.getInstance().setPrimaryStage(primaryStage);
        Handler.getInstance().setServer(dataRequest);

        primaryStage.show();

        LoginState loginState = new LoginState();
        loginState.createScene(Handler.getInstance().getPrimaryStage());
        MainScreenState mainScreenState = new MainScreenState();
        mainScreenState.createScene(Handler.getInstance().getPrimaryStage());
        DraftScreenState draftScreenState = new DraftScreenState();
        draftScreenState.createScene(Handler.getInstance().getPrimaryStage());


        StateManager.getInstance().addState("LoginState", loginState);
        StateManager.getInstance().addState("MainScreenState", mainScreenState);
        StateManager.getInstance().addState("DraftScreenState", draftScreenState);

        try {
            StateManager.getInstance().setCurrentState("LoginState");
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }
}
