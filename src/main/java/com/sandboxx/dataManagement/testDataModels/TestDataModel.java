package com.sandboxx.dataManagement.testDataModels;

public class TestDataModel {
    public String testID;
    public String username;
    public String password;
    public String email;
    public String phone;
    public String firstName;
    public String lastName;
    public String userData;

    public TestDataModel(String testID, String username, String password, String email, String phone, String firstName, String lastName, String userData) {
        this.testID = testID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userData = userData;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}
