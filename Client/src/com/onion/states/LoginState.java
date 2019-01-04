package com.onion.states;

import com.onion.dataRequests.LoginRequest;
import com.onion.utils.Handler;
import com.onion.utils.PasswordUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.IOException;

public class LoginState implements State{

    private Scene scene;

    private void login(final String username, final String password){
        String response = "";
        //TODO Encrypt password and handle login data

        String salt = PasswordUtils.getSalt(30);
        String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);

        if(!username.equals("Break")){
            try {
                response = Handler.getInstance().getServer().sendRecieveData(new LoginRequest().makeRequest(username + " " + mySecurePassword));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //TODO Change to return msg
        if(response.equals("")){
            try {
                StateManager.getInstance().setCurrentState("MainScreenState");
            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }
        }
    }

    @Override
    public void setScene(final Stage primaryStage) {
        primaryStage.setScene(scene);
    }

    @Override
    public void createScene(final Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        this.scene = new Scene(grid, 300, 275);
        System.out.println(getClass().getResource("resources/theme.css"));
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
        //Title
        Text title = new Text("Welcome");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        //Username and Password Fields
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        //Sign in button
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(e -> {
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("Incorrect Username or Password Entered");
            login(userTextField.getText(), pwBox.getText());
        });
    }
}

