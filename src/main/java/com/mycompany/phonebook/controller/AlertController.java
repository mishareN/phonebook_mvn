package com.mycompany.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Michael Kupryk on 22.04.2017.
 */
public class AlertController {
    @FXML
    private Text titleText;
    @FXML
    private Text warningText;
    @FXML
    private Button okBtn;

    private Stage stage;

    @FXML
    private void initialize() throws ClassNotFoundException{
        titleText.setText("");
        warningText.setText("");
    }

    @FXML
    public void ok (ActionEvent actionEvent)
    {
        stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void start(String title, String warning) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Alert.fxml"));
        Parent root1 = (Parent) loader.load();
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root1, Color.TRANSPARENT));
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
        titleText = new Text();
        titleText.setText(title);
        warningText = new Text();
        warningText.setText(warning);
    }
}
