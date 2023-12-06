package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cars;
import com.techelevator.model.ParkingLot;
import com.techelevator.model.Patrons;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.HashMap;
import java.util.Map;

public class JdbcParkingLotDao implements ParkingLotDao{
    private final JdbcTemplate jdbcTemplate;
    private final CarsDao carsDao;

    public JdbcParkingLotDao(JdbcTemplate jdbcTemplate, CarsDao carsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.carsDao = carsDao;
    }

    @Override
    public ParkingLot getParkingLot() {
        ParkingLot parkingLot = new ParkingLot();

        String sql = "select * from parking_lot";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
           if (results.next()){
                parkingLot = mapRowToParkingLot(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return parkingLot;
    }

    @Override
    public ParkingLot updateParkingLot() {
        return null;
    }

    private ParkingLot mapRowToParkingLot(SqlRowSet results) {
       ParkingLot parkingLot = new ParkingLot();

       parkingLot.setSpotNumber(results.getInt("spot_number"));

        int parkingLotCarId = results.getInt("car_id");
        Cars carId = carsDao.getCarByCarId(parkingLotCarId);
        parkingLot.setCarId(carId);

        parkingLot.setAvailable(results.getBoolean("is_available"));

        Map<Integer,Boolean> parkingSpots = new HashMap<>();
        parkingSpots.put(parkingLot.getSpotNumber(),parkingLot.isAvailable());

        parkingLot.setParkingSpots(parkingSpots);

        return parkingLot;
    }
}
