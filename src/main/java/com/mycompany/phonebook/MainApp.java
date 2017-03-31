package com.mycompany.phonebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Michael Kupryk on 29.03.2017.
 */
public class MainApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("PhoneBook");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 590, 695));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
