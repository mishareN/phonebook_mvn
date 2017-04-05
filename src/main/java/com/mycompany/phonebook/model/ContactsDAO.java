package com.mycompany.phonebook.model;

import com.mycompany.phonebook.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael Kupryk on 03.04.2017.
 */
public class ContactsDAO {
    public static Contacts searchContact (String contId) throws SQLException, ClassNotFoundException {
        String selectStmnt = "SELECT * FROM phonebook.contacts where contact_id=" + contId;
        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(selectStmnt);
            Contacts contacts = getContactsFromResultSet(resultSet);
            return contacts;
        } catch (SQLException e){
            System.out.println("Error occurred while searching an contacts with " + contId + ";    Error: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Contacts getContactsFromResultSet(ResultSet resultSet) throws SQLException{
        Contacts contacts = null;
        if(resultSet.next()){
            contacts = new Contacts();
            contacts.setContact_id(resultSet.getInt("contact_id"));
            contacts.setContact_name(resultSet.getString("contact_name"));
            contacts.setOrganization(resultSet.getString("organization"));
            contacts.setGroup(resultSet.getString("group"));
            contacts.setMobile_number(resultSet.getString("mobile_number"));
            contacts.setOffice_number(resultSet.getString("office_number"));
            contacts.setHome_number(resultSet.getString("home_number"));
            contacts.setFax_number(resultSet.getString("fax_number"));
            contacts.setEmail(resultSet.getString("email"));
            contacts.setWeb(resultSet.getString("web"));
            contacts.setOther_cont(resultSet.getString("other_cont"));
            contacts.setOther_cont2(resultSet.getString("other_cont2"));
            contacts.setAdress(resultSet.getString("adress"));
        }
        return contacts;
    }

    public static ObservableList<Contacts> searchContacts () throws SQLException, ClassNotFoundException{
        String selectStmnt = "SELECT * FROM phonebook.contacts";
        try {
            ResultSet resultSet = DBUtil.dbExecuteQuery(selectStmnt);
            ObservableList<Contacts> contactsList = getContactsList(resultSet);
            return contactsList;
        } catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Contacts> getContactsList(ResultSet resultSet) throws SQLException, ClassNotFoundException{
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        while(resultSet.next()){
            Contacts contacts = new Contacts();
            contacts = new Contacts();
            contacts.setContact_id(resultSet.getInt("contact_id"));
            contacts.setContact_name(resultSet.getString("contact_name"));
            contacts.setOrganization(resultSet.getString("organization"));
            contacts.setGroup(resultSet.getString("group"));
            contacts.setMobile_number(resultSet.getString("mobile_number"));
            contacts.setOffice_number(resultSet.getString("office_number"));
            contacts.setHome_number(resultSet.getString("home_number"));
            contacts.setFax_number(resultSet.getString("fax_number"));
            contacts.setEmail(resultSet.getString("email"));
            contacts.setWeb(resultSet.getString("web"));
            contacts.setOther_cont(resultSet.getString("other_cont"));
            contacts.setOther_cont2(resultSet.getString("other_cont2"));
            contacts.setAdress(resultSet.getString("adress"));
            contactsList.add(contacts);
        }
        return contactsList;
    }

    public static void updateContactEmail(String contact_id, String email) throws SQLException, ClassNotFoundException{
        String updateStmnt =
                "BEGIN\n" +
                        "   UPDATE contacts\n" +
                        "       SET EMAIL = '" + email + "'\n" +
                        "     WHERE contact_id = '" + contact_id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        try {
            DBUtil.dbExecuteUpdate(updateStmnt);
        } catch (SQLException e){
            System.out.println("SQL Update operation has been failed: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteContactWithId (String contact_id) throws SQLException, ClassNotFoundException{
        String updateStmnt =
                "BEGIN\n" +
                        "   DELETE FROM contacts\n" +
                        "       WHERE contact_id = '" + contact_id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        try {
          DBUtil.dbExecuteUpdate(updateStmnt);
        } catch (SQLException e){
            System.out.println("SQL Delete operation has been failed: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertContact (String contact_name, String organization, String group, String mobile_number, String office_number, String home_number, String fax_number, String email, String web, String other_cont, String other_cont2, String adress) throws SQLException, ClassNotFoundException{
        String updateStmnt =
                "BEGIN\n" +
                        "INSERT INTO contacts\n" +
                        "(contact_id, contact_name, organization, group, mobile_number, office_number, home_number, fax_number, email, web, other_cont, other_cont2, adress)\n" +
                        "VALUES\n" +
                        "(sequence_contacts.nextval, '"+contact_name+"', '"+organization+"', '"+group+"', '"+mobile_number+"', '"+office_number+"', '"+home_number+"', '"+fax_number+"', '"+email+"', '"+web+"', '"+other_cont+"', '"+other_cont2+"', '"+adress + ");\n" +
                        "END;";
        try {
            DBUtil.dbExecuteUpdate(updateStmnt);
        } catch (SQLException e){
            System.out.println("SQL Insert operation has been failed: " + e);
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
