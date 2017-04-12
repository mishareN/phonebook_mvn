package com.mycompany.phonebook.controller;

import com.mycompany.phonebook.MainApp;
import com.mycompany.phonebook.model.Contacts;
import com.mycompany.phonebook.model.ContactsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

/**
 * Created by Michael Kupryk on 29.03.2017.
 */
public class Controller {
    @FXML
    private  TextField groupText;
    @FXML
    private TextField mobileText;
    @FXML
    private TextField homeText;
    @FXML
    private TextField officeText;
    @FXML
    private TextField faxText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField webText;
    @FXML
    private TextField otherText;
    @FXML
    private TextField other2Text;
    @FXML
    private TableView<Contacts> contactsTable;
    @FXML
    private TextField searchText;
    @FXML
    private TableColumn<Contacts, Integer> idColumn;
    @FXML
    private TableColumn<Contacts, String> nameColumn;
    @FXML
    private TableColumn<Contacts, String> numberColumn;
    @FXML
    private TableColumn<Contacts, String> groupColumn;
    @FXML
    private TableColumn<Contacts, String> emailColumn;
    @FXML
    private TextField nameText;
    @FXML
    private TextField organizationText;


    public void handleExit (ActionEvent actionEvent)
    {
        System.exit(0);
    }

    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Phonebok. (JavaFX + MySQL)");
        alert.setContentText("The program is designed to store and quickly access information about your contacts.");
        alert.show();
    }

    @FXML
    private void searchContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Contacts cont = ContactsDAO.searchContact(searchText.getText());
            populateAndShowContacts(cont);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred while getting contacts information from DB. \n" + e);
            throw e;
        }
    }

    @FXML
    private void searchContacts() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Contacts> conData = ContactsDAO.searchContacts();
            populateContacts(conData);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred while getting contacts information from DB. \n" + e);
            throw e;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException{
        idColumn.setCellValueFactory(cellData -> cellData.getValue().contactIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().contact_nameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().mobile_numberProperty());
        groupColumn.setCellValueFactory(cellData -> cellData.getValue().groupProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        searchContacts();
        contactsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, Contacts) ->  setContactInfoToTextArea(Contacts));
    }

    @FXML
    private void populateContacts(Contacts contacts) {
        ObservableList<Contacts> contData = FXCollections.observableArrayList();
        contData.add(contacts);
        contactsTable.setItems(contData);
    }

    @FXML
    private void setContactInfoToTextArea(Contacts contacts) {
        nameText.setText(contacts.getContact_name());
        organizationText.setText(contacts.getOrganization());
        groupText.setText(contacts.getGroup());
        mobileText.setText(contacts.getMobile_number());
        homeText.setText(contacts.getHome_number());
        officeText.setText(contacts.getOffice_number());
        faxText.setText(contacts.getFax_number());
        emailText.setText(contacts.getEmail());
        webText.setText(contacts.getWeb());
        otherText.setText(contacts.getOther_cont());
        other2Text.setText(contacts.getOther_cont2());
        addressText.setText(contacts.getAdress());
    }

    @FXML
    private void clearArea(){
        nameText.clear();
        organizationText.clear();
        groupText.clear();
        mobileText.clear();
        homeText.clear();
        officeText.clear();
        faxText.clear();
        emailText.clear();
        webText.clear();
        otherText.clear();
        other2Text.clear();
        addressText.clear();
    }

    @FXML
    private void populateAndShowContacts(Contacts contacts) throws ClassNotFoundException{
        if(contacts!=null){
            populateContacts(contacts);
            setContactInfoToTextArea(contacts);
        } else System.out.println("This contact does not exist!");
    }

    @FXML
    private void populateContacts(ObservableList<Contacts> contData) throws ClassNotFoundException {
        contactsTable.setItems(contData);
    }

    @FXML
    private void updateContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try{
            ContactsDAO.updateContact(contactsTable.getSelectionModel().getSelectedItem(), nameText.getText(), organizationText.getText(), groupText.getText(), mobileText.getText(), homeText.getText(), officeText.getText(), faxText.getText(), emailText.getText(), webText.getText(), otherText.getText(), other2Text.getText(),addressText.getText());
            System.out.println("Contact has been. \n");
            searchContacts();
            clearArea();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating contact: " + e);
        }
    }

    @FXML
    private void insertContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        try{
            ContactsDAO.insertContact(nameText.getText(), organizationText.getText(), groupText.getText(), mobileText.getText(), homeText.getText(), officeText.getText(), faxText.getText(), emailText.getText(), webText.getText(), otherText.getText(), other2Text.getText(),addressText.getText());
            System.out.println("Contact has been inserted! \n");
            searchContacts();
            clearArea();
        } catch (SQLException e){
            System.out.println("Problem occurred while inserting contact: " + e);
            throw e;
        }
    }

    @FXML
    private void deleteContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        try{
            int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
            ContactsDAO.deleteContact(contactsTable.getSelectionModel().getSelectedItem());
            contactsTable.getItems().remove(selectedIndex);
            System.out.println("Contact has been deleted! \n");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Contact Selected");
                alert.setContentText("Please select a contact in a table.");
                alert.showAndWait();
            }
        } catch (SQLException e){
            System.out.println("Problem occurred while deleting contact: " + e);
            throw e;
        }
    }

    @FXML
    private void sendMail(ActionEvent actionEvent) {

    }
}
