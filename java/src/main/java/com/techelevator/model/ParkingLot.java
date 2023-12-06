package com.techelevator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    Map<Integer, Boolean> parkingSpots = new HashMap<>();
    private int spotNumber;
    private Cars carId;
    private boolean isAvailable;

    public Map<Integer, Boolean> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(Map<Integer, Boolean> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Cars getCarId() {
        return carId;
    }

    public void setCarId(Cars carId) {
        this.carId = carId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}