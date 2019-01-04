package com.onion.states;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainScreenState implements State{

    private Scene scene;

    @Override
    public void setScene(final Stage primaryStage) {
        primaryStage.setScene(scene);
    }

    @Override
    public void createScene(final Stage primaryStage) {
        //Logout Button
        Button logoutBtn = new Button("Log Out");
        HBox hbLogoutBtn = new HBox(10);
        logoutBtn.setLayoutX(1000.0);
        logoutBtn.setLayoutY(100.0);
        hbLogoutBtn.getChildren().add(logoutBtn);


        // Draft Button
        Button draftBtn = new Button("Join Draft");
        HBox hbDraftBtn = new HBox(10);
        draftBtn.setLayoutX(1000.0);
        draftBtn.setLayoutY(400.0);
        hbDraftBtn.getChildren().add(draftBtn);

        Pane pane = new Pane();
        pane.getChildren().add(logoutBtn);
        pane.getChildren().add(draftBtn);
        this.scene = new Scene(pane, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());

        logoutBtn.setOnAction(e -> {
            logout();

        });
    }

    private void logout(){

    }
}
