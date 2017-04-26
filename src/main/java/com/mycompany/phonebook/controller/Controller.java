package com.mycompany.phonebook.controller;

import com.mycompany.phonebook.model.Contacts;
import com.mycompany.phonebook.model.ContactsDAO;
import com.mycompany.phonebook.view.Dialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by Michael Kupryk on 29.03.2017.
 */
public class Controller {
    private Stage STAGE;

    private double xOffset;
    private double yOffset;

    @FXML
    private Label labelText;
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
    private  Button minimizeButton;
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
    @FXML
    private AnchorPane headerPane;

    private ObservableList<Contacts> conData = FXCollections.observableArrayList();

    public void handleExit (ActionEvent actionEvent)
    {
        System.exit(0);
    }

    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Phone Book. (JavaFX + MySQL)");
        alert.setContentText("The program is designed to store and quickly access information about your contacts.");
        alert.show();
    }

    private void searchContacts() throws SQLException, ClassNotFoundException {
        try {
            conData = ContactsDAO.searchContacts();
            populateContacts(conData);
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error occurred while getting contacts information from DB. \n" + e);
            throw e;
        }
    }

    private void populateContacts(ObservableList<Contacts> _contData) throws ClassNotFoundException {
        FilteredList<Contacts> filteredList = new FilteredList<>(_contData, p -> true);
        searchText.textProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(contacts -> {
                    if (newValue == null && newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (contacts.getContact_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                }));
        SortedList<Contacts> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(contactsTable.comparatorProperty());
        contactsTable.setItems(sortedList);

        contactsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, Contacts) ->  setContactInfoToTextArea(Contacts));
    }

    public void Initialize(Stage stage){
        STAGE = stage;
        headerPane.setOnMousePressed(event -> {
            xOffset = STAGE.getX() - event.getScreenX();
            yOffset = STAGE.getY() - event.getScreenY();
        });
        headerPane.setOnMouseDragged(event ->{
            STAGE.setX(event.getScreenX() + xOffset);
            STAGE.setY(event.getScreenY() + yOffset);
        });
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException{
        minimizeButton.setOnAction(event->{
            STAGE.setIconified(true);
        });
        idColumn.setCellValueFactory(cellData -> cellData.getValue().contactIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().contact_nameProperty());
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().mobile_numberProperty());
        groupColumn.setCellValueFactory(cellData -> cellData.getValue().groupProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        searchContacts();
        setCountRecords();
        populateContacts(conData);
    }

    private void setContactInfoToTextArea(Contacts contacts) {
        if(contacts == null) return;
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
        searchText.clear();
    }

    @FXML
    private void updateContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        try{
            int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                ContactsDAO.updateContact(contactsTable.getSelectionModel().getSelectedItem(), nameText.getText(), organizationText.getText(), groupText.getText(), mobileText.getText(),  officeText.getText(), homeText.getText(), faxText.getText(), emailText.getText(), webText.getText(), otherText.getText(), other2Text.getText(),addressText.getText());
                System.out.println("Contact has been. \n");
                searchContacts();
                clearArea();
                setCountRecords();
            } else {
                Dialogs.showAlertDialog("No Contact Selected!", "Please select a contact in a table.");
            }
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating contact: " + e);
        } catch (Exception e) {
            System.out.println("Problem with Alert form: " + e);
        }
    }

    private void setCountRecords() throws ClassNotFoundException, SQLException {
        int count = ContactsDAO.getCountRecords();
        labelText.setText("Number of records in the phone book: " + String.valueOf(count));
    }

    @FXML
    private void insertContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        try{
            if(!nameText.getText().equals("")) {
                ContactsDAO.insertContact(nameText.getText(), organizationText.getText(), groupText.getText(), mobileText.getText(), officeText.getText(), homeText.getText(), faxText.getText(), emailText.getText(), webText.getText(), otherText.getText(), other2Text.getText(), addressText.getText());
                System.out.println("Contact has been inserted! \n");
                searchContacts();
                clearArea();
                setCountRecords();
            } else{
                Dialogs.showAlertDialog("Invalid input!", "Some fields are not filled, please fill out the main fields.");
            }
        } catch (SQLException e){
            System.out.println("Problem occurred while inserting contact: " + e);
            throw e;
        } catch (Exception e) {
            System.out.println("Problem with Alert form: " + e);
        }
    }

    @FXML
    private void deleteContact(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        try{
            int selectedIndex = contactsTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                ContactsDAO.deleteContact(contactsTable.getSelectionModel().getSelectedItem());
                searchContacts();
                clearArea();
                setCountRecords();
                System.out.println("Contact has been deleted! \n");
            } else {
                Dialogs.showAlertDialog("No Contact Selected!", "Please select a contact in a table.");
            }
        } catch (SQLException e){
            System.out.println("Problem occurred while deleting contact: " + e);
            throw e;
        }
        catch (Exception e) {
            System.out.println("Problem with Alert form: " + e);
        }
    }
}