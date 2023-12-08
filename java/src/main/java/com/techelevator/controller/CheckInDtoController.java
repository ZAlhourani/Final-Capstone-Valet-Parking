package com.techelevator.controller;

import com.techelevator.dao.CarsDao;
import com.techelevator.dao.PatronsDao;
import com.techelevator.dao.SlipsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Cars;
import com.techelevator.model.CheckInDto;
import com.techelevator.model.Patrons;
import com.techelevator.model.Slips;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CheckInDtoController {

private final SlipsDao slipsDao;
private final UserDao userDao;
private final PatronsDao patronsDao;
private final CarsDao carsDao;

    public CheckInDtoController(SlipsDao slipsDao, UserDao userDao, PatronsDao patronsDao, CarsDao carsDao) {
        this.slipsDao = slipsDao;
        this.userDao = userDao;
        this.patronsDao = patronsDao;
        this.carsDao = carsDao;
    }
     @PreAuthorize("hasRole('ROLE_VALET')")
     @PostMapping("/check-in")
     public Slips checkIn(@RequestBody CheckInDto checkInDto){
         Slips newSlip = new Slips();
         Cars newCar = new Cars();
         Patrons newPatron = new Patrons();

         newCar.setMake(checkInDto.getMake());
         newCar.setModel(checkInDto.getModel());
         newCar.setColor(checkInDto.getColor());
         newCar.setLicensePlate(checkInDto.getLicensePlate());
         newCar.setVinNumber(checkInDto.getVinNumber());

         carsDao.createNewCar(newCar);

         newPatron.setName(checkInDto.getName());
         newPatron.setPhoneNumber(checkInDto.getPhoneNumber());
         patronsDao.createNewPatron(newPatron);

         newSlip.setArrivalTime(checkInDto.getArrivalTime());
         slipsDao.createNewSlip(newSlip);
         return newSlip;
     }
}
