package com.techelevator.model;

import java.time.LocalDateTime;

public class Slips {

    private int slipNumber;
    private int patronId;
    private String vinNumber;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private double hourlyPrice;
    private double total;

    public int getSlipNumber() {
        return slipNumber;
    }

    public void setSlipNumber(int slipNumber) {
        this.slipNumber = slipNumber;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getHourlyPrice() {
        return hourlyPrice;
    }

    public void setHourlyPrice(double hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}