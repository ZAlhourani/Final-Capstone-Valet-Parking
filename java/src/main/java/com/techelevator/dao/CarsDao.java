package com.techelevator.dao;

import com.techelevator.model.Cars;

import java.util.List;

public interface CarsDao {

    List<Cars> getAllCars();
    Cars getCarByCarId(int carId);
    Cars getCarByPatronId(int patronId);
    Cars createNewCar(Cars newCar);
    Cars updateCar(Cars car);

}
