package com.mycompany.phonebook.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public void setText(String title, String warning){
        titleText.setText(title);
        warningText.setText(warning);
    }

    @FXML
    public void ok (ActionEvent actionEvent)
    {
        stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}
