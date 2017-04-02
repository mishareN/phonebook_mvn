package com.mycompany.phonebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Michael Kupryk on 29.03.2017.
 */
public class MainApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Sample.fxml"));
        primaryStage.setTitle("PhoneBook");
        primaryStage.setResizable(false);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(new Scene(root, 590, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
