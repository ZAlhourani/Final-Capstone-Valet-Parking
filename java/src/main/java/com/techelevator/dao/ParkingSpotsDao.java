package com.techelevator.dao;

import com.techelevator.model.ParkingSpots;

import java.util.List;

public interface ParkingSpotsDao {

    List<ParkingSpots> getAllParkingSpots();
    ParkingSpots getParkingSpotBySpotNumber(int spotNumber);
    ParkingSpots getParkingSpotByAvailability(boolean isAvailable);
    ParkingSpots updateParkingSpot(ParkingSpots parkingSpot);
}
