/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

/**
 *
 * @author ANTER
 */
public class Contact {
    int id;
    String fName;
    String mName;
    String lName;
    String phoneNumber;
    String email;

    public Contact(int id,String fName, String mName, String lName, String phoneNumber, String email ) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id= id;
    }
    public Contact(String fName, String mName, String lName, String phoneNumber, String email ) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
