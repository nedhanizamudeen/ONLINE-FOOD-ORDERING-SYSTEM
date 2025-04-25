package com.ofos.model;

public class Address {
    private int userId;
    private String streetAddress;
    private String pinCode;
    private String city;
    private String state;

    public Address(int userId, String streetAddress, String pinCode, String city, String state) {
        this.userId = userId;
        this.streetAddress = streetAddress;
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}