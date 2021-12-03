package com.example.cinematesdesktop.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginAdmin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginAdmin.class.getResource("../login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("com/example/cinematesdesktop/style.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(new Image("/images/CM20.png"));
    }

    public static void main(String[] args) {
        launch();
    }
}