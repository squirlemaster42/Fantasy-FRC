package com.onion.states;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class JoinPopup {


    public static void display()
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Join Draft");
       // popupwindow.getStylesheets().add(JoinPopup.class.getResource("theme.css").toExternalForm());


        Label label1= new Label("Join");

        TextField keyTextField = new TextField();


        Button button1= new Button("Create Draft");


        button1.setOnAction(e -> popupwindow.close());



        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, keyTextField, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}
