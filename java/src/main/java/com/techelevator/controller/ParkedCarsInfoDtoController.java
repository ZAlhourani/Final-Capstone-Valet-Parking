package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.ParkedCarsInfoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class ParkedCarsInfoDtoController {


   private final ParkedCarsInfoDtoDao parkedCarsInfoDtoDao;

    public ParkedCarsInfoDtoController(ParkedCarsInfoDtoDao parkedCarsInfoDtoDao) {
        this.parkedCarsInfoDtoDao = parkedCarsInfoDtoDao;
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/car-info")
    public List<ParkedCarsInfoDto> getParkedCarsInfo(){

        List<ParkedCarsInfoDto> parkedCarsInfoDtoList = new ArrayList<>();

        return parkedCarsInfoDtoDao.getParkedCarsInfo();

    }

        ParkedCarsInfoDto parkedCarInfo = new ParkedCarsInfoDto();

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

  //  }
}
