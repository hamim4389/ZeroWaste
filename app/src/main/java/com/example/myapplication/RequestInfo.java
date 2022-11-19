package com.example.myapplication;

public class RequestInfo {

    public String description,adress,contact;

    public RequestInfo() {
    }

    public RequestInfo(String description, String adress, String contact) {
        this.description = description;
        this.adress = adress;
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
