package com.example.login.model;

public class DataStore {
    private String fullName;
    private String Email;
    private String passWord;

    public DataStore(String fullName, String Email, String passWord) {
        this.fullName = fullName;
        this.Email = Email;
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
