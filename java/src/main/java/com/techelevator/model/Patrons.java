package com.techelevator.model;

public class Patrons {
    private int patronId;
    private User user;
    private String name;
    private int phoneNumber;

    public Patrons(int patronId, User user, String name, int phoneNumber) {
        this.patronId = patronId;
        this.user = user;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
