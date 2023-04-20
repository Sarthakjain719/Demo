package com.example.myapplication.models;

public class User {
    private String fullname;
    private String Password;
    private String Phone;

//    private String balance;



    public String getFullname() {
        return fullname;
    }

    public User(String fullname, String password, String phone) {
        this.fullname = fullname;
        Password = password;
        Phone = phone;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
