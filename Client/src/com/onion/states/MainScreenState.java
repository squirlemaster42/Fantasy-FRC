package com.onion.states;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;

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
        Label yourDrafts = new Label("Your Drafts");
        yourDrafts.setLayoutX(500.0);
        yourDrafts.setLayoutY(100.0);
        Label requestScores = new Label("Request Scores");
        requestScores.setLayoutX(100.0);
        requestScores.setLayoutY(100.0);


        Pane pane = new Pane();
        pane.getChildren().add(logoutBtn);
        pane.getChildren().add(draftBtn);
        pane.getChildren().add(yourDrafts);
        pane.getChildren().add(requestScores);
        this.scene = new Scene(pane, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());

        logoutBtn.setOnAction(e -> {

            try {
                StateManager.getInstance().setCurrentState("LoginState");
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }

        });
     /*   draftBtn.setOnAction(e -> {

            try {

            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }

        });
     */
    }

    private void logout(){

    }
}
