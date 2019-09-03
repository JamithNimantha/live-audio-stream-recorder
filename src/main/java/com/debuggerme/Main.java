package com.debuggerme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/com/debuggerme/view/Home.fxml"));

        Scene temp = new Scene(parent);
        primaryStage.setScene(temp);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icons8-record-100.png")));

        primaryStage.show();

    }
}
