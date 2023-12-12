package com.techelevator.model;

import org.springframework.security.core.parameters.P;

import java.lang.ref.PhantomReference;

public class Cars {
    private int carId;
    private String make;
    private String model;
    private String color;
    private String licensePlate;
    private String vinNumber;
    private Patrons patronId;

//    /* added */ private String ownerName;
//    /* added */ private String ownerContact;
//
//
//    /* added */ public String getOwnerName() {
//        return ownerName;
//    }
//
//    /* added */ public void setOwnerName(String ownerName) {
//        this.ownerName = ownerName;
//    }
//
//    /* added */ public String getOwnerContact() {
//        return ownerContact;
//    }
//
//    /* added */ public void setOwnerContact(String ownerContact) {
//        this.ownerContact = ownerContact;
//    }
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Patrons getPatronId() {
        return patronId;
    }

    public void setPatronId(Patrons patronId) {
        this.patronId = patronId;
    }
}
