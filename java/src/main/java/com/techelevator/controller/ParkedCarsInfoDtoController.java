package com.techelevator.controller;

import com.techelevator.dao.CarsDao;
import com.techelevator.dao.ParkingSpotsDao;
import com.techelevator.dao.PatronsDao;
import com.techelevator.dao.SlipsDao;
import com.techelevator.model.ParkedCarsInfoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class ParkedCarsInfoDtoController {

    private final CarsDao carsDao;
    private final PatronsDao patronsDao;
    private final ParkingSpotsDao parkingSpotsDao;
    private final SlipsDao slipsDao;

    public ParkedCarsInfoDtoController(CarsDao carsDao, PatronsDao patronsDao, ParkingSpotsDao parkingSpotsDao, SlipsDao slipsDao) {
        this.carsDao = carsDao;
        this.patronsDao = patronsDao;
        this.parkingSpotsDao = parkingSpotsDao;
        this.slipsDao = slipsDao;
    }

//    @PreAuthorize("hasRole('ROLE_VALET')")
//    @GetMapping("/car-info")
//    public List<ParkedCarsInfoDto> getParkedCarsInfo(@RequestBody ParkedCarsInfoDto parkedCarsInfoDto){
//
//        List<ParkedCarsInfoDto> parkedCarsInfoDtoList = new ArrayList<>();
//
//        parkedCarsInfoDtoList.add(parkedCarsInfoDto);
//
//        return parkedCarsInfoDtoList;
//
//    }

//        ParkedCarsInfoDto parkedCarInfo = new ParkedCarsInfoDto();
//
//        parkedCarInfo.setMake(parkedCarsInfoDto.getMake());
//        parkedCarInfo.setModel(parkedCarsInfoDto.getModel());
//        parkedCarInfo.setColor(parkedCarsInfoDto.getColor());
//        parkedCarInfo.setLicensePlate(parkedCarsInfoDto.getLicensePlate());
//        parkedCarInfo.setVinNumber(parkedCarsInfoDto.getVinNumber());
//        parkedCarInfo.setName(parkedCarsInfoDto.getName());
//        parkedCarInfo.setPhoneNumber(parkedCarsInfoDto.getPhoneNumber());
//        parkedCarInfo.setSpotNumber(parkedCarsInfoDto.getSpotNumber());
//        parkedCarInfo.setTotal(parkedCarsInfoDto.getTotal());
//
//        return parkedCarInfo;
//
//    }
}
