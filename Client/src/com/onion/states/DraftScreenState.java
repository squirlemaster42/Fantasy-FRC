package com.onion.states;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class DraftScreenState implements State {

    private Scene scene;

    @Override
    public void setScene(final Stage primaryStage) {
        primaryStage.setScene(scene);
    }

    @Override
    public void createScene(final Stage primaryStage) {

        //Go Back
        Button backBtn = new Button("Back");
        HBox hbbackBtn = new HBox(10);
        backBtn.setLayoutX(1000.0);
        backBtn.setLayoutY(1.0);
        hbbackBtn.getChildren().add(backBtn);


        Pane pane = new Pane();
        this.scene = new Scene(pane, 1100, 800);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        pane.getChildren().add(backBtn);

        backBtn.setOnAction(e -> {
            try {
                StateManager.getInstance().setCurrentState("MainScreenState");
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }


        });

    }
}
