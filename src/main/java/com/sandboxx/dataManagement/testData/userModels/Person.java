package com.sandboxx.dataManagement.testData.userModels;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;

    public Person(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFormattedSenderInfo() {
        return firstName + " " + lastName + "\n" + address.getAddress() + "\n" +
                address.getCity() + ", " + address.getState() + " " + address.getZipCode();
    }

    public String getEditedFullName() {
        return lastName + ", " + firstName;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
