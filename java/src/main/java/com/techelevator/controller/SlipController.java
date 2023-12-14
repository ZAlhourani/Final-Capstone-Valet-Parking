package com.techelevator.controller;


import com.techelevator.dao.SlipsDao;
import com.techelevator.model.Slips;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class SlipController {

    private final SlipsDao slipsDao;

    public SlipController(SlipsDao slipsDao) {
        this.slipsDao = slipsDao;
    }
    @GetMapping("/slips")
    public List<Slips> getAllSlipsList(){
        return slipsDao.getAllSlips();
    }

   @PreAuthorize("hasRole('ROLE_VALET')")
   @GetMapping("/slips/{slipNumber}")
   public Slips getSlipBySlipNumber (@PathVariable int slipNumber) {
       Slips result = slipsDao.getSlipBySlipNumber(slipNumber);
       if (result == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No slip with that number.");
       } else {
           return result;
       }
   }

    @GetMapping("/slips/patrons/{patronId}")
    public List<Slips> getSlipsByPatronId (@PathVariable int patronId){
        List<Slips> result = slipsDao.getSlipByPatronId(patronId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No slip with that number.");
        } else {
            return result;
        }
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/slips/cars/{carId}")
    public List<Slips> getSlipByCarId (@PathVariable int carId){
        List<Slips> result = slipsDao.getSlipByCarId(carId);

        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No slip with that number.");
        } else {
            return result;
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/slips")
    public Slips createNewSlip (@RequestBody Slips newSlip) {
        return slipsDao.createNewSlip(newSlip);
    }

    @PutMapping("/slips/{slipNumber}")
    public Slips updateSlip (@PathVariable int slipNumber, @RequestBody Slips updatedSlip) {
        updatedSlip.setSlipNumber(slipNumber);

        return slipsDao.updateSlip(updatedSlip);
    }

}
