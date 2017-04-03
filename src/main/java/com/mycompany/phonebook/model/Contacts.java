package com.mycompany.phonebook.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Michael Kupryk on 03.04.2017.
 */
public class Contacts {
    private IntegerProperty contact_id;
    private StringProperty contact_name;
    private StringProperty organization;
    private StringProperty group;
    private StringProperty mobile_number;
    private StringProperty home_number;
    private StringProperty fax_number;
    private StringProperty email;
    private StringProperty web;
    private StringProperty other_cont;
    private StringProperty other_cont2;
    private StringProperty adress;

    public Contacts(){
        this.contact_id = new SimpleIntegerProperty();
        this.contact_name = new SimpleStringProperty();
        this.organization = new SimpleStringProperty();
        this.group = new SimpleStringProperty();
        this.mobile_number = new SimpleStringProperty();
        this.home_number = new SimpleStringProperty();
        this.fax_number = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.web = new SimpleStringProperty();
        this.other_cont = new SimpleStringProperty();
        this.other_cont2 = new SimpleStringProperty();
        this.adress = new SimpleStringProperty();
    }

    public int getContact_id(){
        return contact_id.get();
    }

    public void setContact_id(int contactId){
        this.contact_id.setValue(contactId);
    }

    public IntegerProperty contactIdProperty(){
        return contact_id;
    }

    public String getContact_name() {
        return contact_name.get();
    }

    public StringProperty contact_nameProperty() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name.set(contact_name);
    }

    public String getOrganization() {
        return organization.get();
    }

    public StringProperty organizationProperty() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization.set(organization);
    }

    public String getGroup() {
        return group.get();
    }

    public StringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public String getMobile_number() {
        return mobile_number.get();
    }

    public StringProperty mobile_numberProperty() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number.set(mobile_number);
    }

    public String getHome_number() {
        return home_number.get();
    }

    public StringProperty home_numberProperty() {
        return home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number.set(home_number);
    }

    public String getFax_number() {
        return fax_number.get();
    }

    public StringProperty fax_numberProperty() {
        return fax_number;
    }

    public void setFax_number(String fax_number) {
        this.fax_number.set(fax_number);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getWeb() {
        return web.get();
    }

    public StringProperty webProperty() {
        return web;
    }

    public void setWeb(String web) {
        this.web.set(web);
    }

    public String getOther_cont() {
        return other_cont.get();
    }

    public StringProperty other_contProperty() {
        return other_cont;
    }

    public void setOther_cont(String other_cont) {
        this.other_cont.set(other_cont);
    }

    public String getOther_cont2() {
        return other_cont2.get();
    }

    public StringProperty other_cont2Property() {
        return other_cont2;
    }

    public void setOther_cont2(String other_cont2) {
        this.other_cont2.set(other_cont2);
    }

    public String getAdress() {
        return adress.get();
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }
}
