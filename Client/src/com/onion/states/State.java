package com.onion.states;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface State{
    void setScene(final Stage primaryStage);
    void createScene(final Stage primaryStage);
}
