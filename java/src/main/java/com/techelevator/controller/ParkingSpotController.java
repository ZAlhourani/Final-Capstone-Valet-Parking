package com.techelevator.controller;

import com.techelevator.dao.CarsDao;
import com.techelevator.dao.ParkingSpotsDao;
import com.techelevator.dao.PatronsDao;
import com.techelevator.model.ParkingSpots;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class ParkingSpotController {

    private final ParkingSpotsDao parkingSpotsDao;

    public ParkingSpotController(CarsDao carsDao, ParkingSpotsDao parkingSpotsDao, PatronsDao patronsDao) {
        this.parkingSpotsDao = parkingSpotsDao;

    }
    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/parking-spots")
    public List<ParkingSpots> getParkingSpotsList(){
        return parkingSpotsDao.getAllParkingSpots();
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/parking-spots/{spotNumber}")
    public ParkingSpots getParkingSpotBySpotNumber (@PathVariable int spotNumber) {
        ParkingSpots result = parkingSpotsDao.getParkingSpotBySpotNumber(spotNumber);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No parking spot with that id.");
        } else {
            return result;
        }
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/parking-spots/{isAvailable}/parked-cars")

    public List<ParkingSpots> getParkingSpotsWithCars (@PathVariable boolean isAvailable) {

        return parkingSpotsDao.getParkingSpotsWithCars(isAvailable);
    }

    @PermitAll
    @GetMapping("/parking-spots/availability/{isAvailable}")
    public List<ParkingSpots> getParkingSpotByAvailability (@PathVariable boolean isAvailable) {

         return parkingSpotsDao.getParkingSpotByAvailability(isAvailable);
    }
    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/parking-spots/cars/{carId}")
    public ParkingSpots getParkingSpotByCarId (@PathVariable int carId){
        return parkingSpotsDao.getParkingSpotByCarId(carId);
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @PutMapping("/parking-spots/spots/{spotNumber}")
    public ParkingSpots updateParkingSpot (@PathVariable int spotNumber, @RequestBody ParkingSpots updatedParkingSpot) {
        updatedParkingSpot.setSpotNumber(spotNumber);
        return parkingSpotsDao.updateParkingSpot(updatedParkingSpot);
    }
}
