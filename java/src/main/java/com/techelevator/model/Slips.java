package com.techelevator.model;

import java.time.LocalDateTime;

public class Slips {

    private int slipNumber;
    private Patrons patronId;
    private Cars carId;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private double hourlyPrice = 5.00;
    private double total;

    public int getSlipNumber() {
        return slipNumber;
    }

    public void setSlipNumber(int slipNumber) {
        this.slipNumber = slipNumber;
    }

    public Patrons getPatronId() {
        return patronId;
    }

    public void setPatronId(Patrons patronId) {
        this.patronId = patronId;
    }

    public Cars getCarId() {
        return carId;
    }

    public void setCarId(Cars carId) {
        this.carId = carId;
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
