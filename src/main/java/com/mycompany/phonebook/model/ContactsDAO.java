package com.mycompany.phonebook.model;

import com.mycompany.phonebook.util.DBUtil;

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
}
