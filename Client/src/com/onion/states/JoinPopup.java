package com.onion.states;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class JoinPopup {


    public void display()
    {
        Stage popupwindow =new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Join Draft");


        Label label1= new Label("Join");

        TextField keyTextField = new TextField();


        Button button1= new Button("Create Draft");


        button1.setOnAction(e -> {
                    State state = StateManager.getInstance().getCurrentState();
                    if (state instanceof MainScreenState){
                        ((MainScreenState) (state)).addLink(keyTextField.getText());


                    }
                    popupwindow.close();

                }
        );



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, keyTextField, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene= new Scene(layout, 300, 250);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());


        popupwindow.setScene(scene);

        popupwindow.showAndWait();

    }


}
