package com.techelevator.controller;

import com.techelevator.dao.CarsDao;
import com.techelevator.dao.PatronsDao;
import com.techelevator.dao.SlipsDao;
import com.techelevator.model.ParkedCarDetailsDto;
import com.techelevator.model.Slips;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class ParkedCarDetailsController {

    private CarsDao carsDao;
    private PatronsDao patronsDao;
    private SlipsDao slipsDao;

    public ParkedCarDetailsController(CarsDao carsDao, PatronsDao patronsDao, SlipsDao slipsDao) {
        this.carsDao = carsDao;
        this.patronsDao = patronsDao;
        this.slipsDao = slipsDao;
    }

    @GetMapping("/car-details/{carId}")
    public ParkedCarDetailsDto getCarDetails(@PathVariable int carId) {

        ParkedCarDetailsDto carDetails = new ParkedCarDetailsDto();

        carDetails.setMake(carsDao.getCarByCarId(carId).getMake());
        carDetails.setModel(carsDao.getCarByCarId(carId).getModel());
        carDetails.setColor(carsDao.getCarByCarId(carId).getColor());
        carDetails.setLicensePlate(carsDao.getCarByCarId(carId).getLicensePlate());
        carDetails.setVinNumber(carsDao.getCarByCarId(carId).getVinNumber());
        carDetails.setName(carsDao.getCarByCarId(carId).getPatronId().getName());
        carDetails.setPhoneNumber(carsDao.getCarByCarId(carId).getPatronId().getPhoneNumber());
        carDetails.setTotal(slipsDao.getSlipByCarId(carId).getTotal());

        return carDetails;
    }



}
