package com.techelevator.dao;

import com.techelevator.model.Slips;

import java.util.List;

public interface SlipsDao {

    List<Slips> getAllSlips();
    Slips getSlipBySlipNumber(int slipNumber);
    List<Slips> getSlipByPatronId(int patronId);
    Slips getSlipByCarId(int carId);
    Slips createNewSlip(Slips newSlip);
    Slips updateSlip(Slips slip);
}
