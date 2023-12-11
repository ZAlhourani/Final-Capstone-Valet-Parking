package com.techelevator.dao;

import com.techelevator.model.ParkingSpots;

import java.util.List;

public interface ParkingSpotsDao {

    List<ParkingSpots> getAllParkingSpots();
    List<ParkingSpots> getParkingSpotsWithCars(boolean isAvailable);
    ParkingSpots getParkingSpotByCarId(int carId);
    ParkingSpots getParkingSpotBySpotNumber(int spotNumber);
    List<ParkingSpots> getParkingSpotByAvailability(boolean isAvailable);
    ParkingSpots updateParkingSpot(ParkingSpots parkingSpot);
}
