package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CheckInDtoController {

    private final SlipsDao slipsDao;
    private final UserDao userDao;
    private final PatronsDao patronsDao;
    private final CarsDao carsDao;
    private final ParkingSpotsDao parkingSpotsDao;

    public CheckInDtoController(SlipsDao slipsDao, UserDao userDao, PatronsDao patronsDao, CarsDao carsDao, ParkingSpotsDao parkingSpotsDao) {
        this.slipsDao = slipsDao;
        this.userDao = userDao;
        this.patronsDao = patronsDao;
        this.carsDao = carsDao;
        this.parkingSpotsDao = parkingSpotsDao;
    }
    @PreAuthorize("hasRole('ROLE_VALET')")
    @PostMapping("/check-in")
    public Slips checkIn(@RequestBody CheckInDto checkInDto){

        Slips newSlip = new Slips();
        Cars newCar = new Cars();
        ParkingSpots newParkingSpot = parkingSpotsDao.getParkingSpotBySpotNumber(checkInDto.getSpotNumber());


        newCar.setMake(checkInDto.getMake());
        newCar.setModel(checkInDto.getModel());
        newCar.setColor(checkInDto.getColor());
        newCar.setLicensePlate(checkInDto.getLicensePlate());
        newCar.setVinNumber(checkInDto.getVinNumber());
        newCar.setPatronId(patronsDao.getPatronByPhoneNumber(checkInDto.getPhoneNumber()));

        newCar = carsDao.createNewCar(newCar);

        if (newParkingSpot.isAvailable()) {

            newParkingSpot.setSpotNumber(checkInDto.getSpotNumber());
            newParkingSpot.setCarId(newCar);

            newParkingSpot.setAvailable(false);

            parkingSpotsDao.updateParkingSpot(newParkingSpot);

        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        newSlip.setArrivalTime(checkInDto.getArrivalTime());
//        newSlip.setDepartureTime(LocalDateTime.now());
        newSlip.setPatronId(patronsDao.getPatronByPhoneNumber(checkInDto.getPhoneNumber()));
        newSlip.setCarId(newCar);
        newSlip = slipsDao.createNewSlip(newSlip);
        return newSlip;
    }

    @PutMapping("/check-out")
    public Slips checkOutCar(@RequestBody Slips slip){

        ParkingSpots newParkingSpot = new ParkingSpots();

        if (!newParkingSpot.isAvailable()) {
            newParkingSpot.setCarId(null);
            newParkingSpot.setAvailable(true);
            parkingSpotsDao.updateParkingSpot(newParkingSpot);

            Slips currentSlip = slipsDao.getSlipBySlipNumber(slip.getSlipNumber());

            currentSlip.setDepartureTime(slip.getDepartureTime());
            currentSlip.setTotal(slip.getTotal());

            return slipsDao.updateSlip(currentSlip);
        }
        return slip;
    }
}















