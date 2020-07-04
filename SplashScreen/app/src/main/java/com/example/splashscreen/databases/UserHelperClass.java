package com.example.splashscreen.databases;

public class UserHelperClass {

    String fullName,userName,email,password,date,gender,phoneNumber;

    public UserHelperClass(){};

    public UserHelperClass(String fullName, String userName, String email, String password, String date, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
