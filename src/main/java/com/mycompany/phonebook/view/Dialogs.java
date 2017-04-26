package com.mycompany.phonebook.view;

import com.mycompany.phonebook.controller.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Michael Kupryk on 26.04.2017.
 */
public class Dialogs {
    static public void showAlertDialog(String title, String warning) throws Exception{
        FXMLLoader loader = new FXMLLoader(Dialogs.class.getResource("/fxml/Alert.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root1, Color.TRANSPARENT));
        stage.setAlwaysOnTop(true);
        AlertController controller = loader.getController();
        controller.setText("Invalid input!", "Some fields are not filled, please fill out the main fields.");
        stage.show();
    }
}
