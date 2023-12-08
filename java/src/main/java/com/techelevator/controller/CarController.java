package com.techelevator.controller;

import com.techelevator.dao.CarsDao;
import com.techelevator.model.Cars;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CarController {

    private final CarsDao carsDao;


    public CarController(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/cars/{carId}")
    public Cars getCarById (@PathVariable int carId){
        Cars result = carsDao.getCarByCarId(carId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No car with that id.");
        } else {
            return result;
        }
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @PostMapping("/cars")
    public Cars createNewCar (@RequestBody Cars newCar) {
        return carsDao.createNewCar(newCar);
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @PutMapping("/cars/{carId}")
    public Cars updateCar (@PathVariable int carId, @RequestBody Cars updatedCar) {
        updatedCar.setCarId(carId);

        return carsDao.updateCar(updatedCar);
    }
}
