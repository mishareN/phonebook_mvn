package com.mycompany.phonebook.controller;

import javafx.scene.control.Alert;

/**
 * Created by Michael Kupryk on 29.03.2017.
 */
public class Controller {
    public void handleExit ()
    {
        System.exit(0);
    }

    public void handleHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Phonebok. (JavaFX + MySQL)");
        alert.setContentText("The program is designed to store and quickly access information about your contacts.");
        alert.show();
    }
}
