package com.questgame.quest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Quest extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Quest.class.getResource("GUI.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Quest");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        stage.centerOnScreen();
        System.out.println("dimensions are: " + d.width + " x " + d.height);
    }


    public static void main(String[] args) {
        launch();
    }
}