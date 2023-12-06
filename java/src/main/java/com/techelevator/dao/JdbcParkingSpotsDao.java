package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Cars;
import com.techelevator.model.ParkingSpots;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcParkingSpotsDao implements ParkingSpotsDao {

    private final JdbcTemplate jdbcTemplate;

    private final CarsDao carsDao;

    public JdbcParkingSpotsDao(JdbcTemplate jdbcTemplate, CarsDao carsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.carsDao = carsDao;
    }

    @Override
    public List<ParkingSpots> getAllParkingSpots() {

        List<ParkingSpots> allParkingSpots = new ArrayList<>();

        String sql = "select * from parking_spots";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

            while (results.next()) {
                ParkingSpots parkingSpot = mapRowToParkingSpots(results);
                allParkingSpots.add(parkingSpot);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allParkingSpots;
    }

    @Override
    public ParkingSpots getParkingSpotBySpotNumber(int spotNumber) {

        ParkingSpots parkingSpot = new ParkingSpots();

        String sql = "select * from parking_spots where spot_number = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, spotNumber);

            if (results.next()) {
                parkingSpot = mapRowToParkingSpots(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return parkingSpot;
    }

    @Override
    public ParkingSpots getParkingSpotByAvailability(boolean isAvailable) {

        ParkingSpots parkingSpot = new ParkingSpots();

        String sql = "select * from parking_spots where is_available = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, isAvailable);

            if (results.next()) {
                parkingSpot = mapRowToParkingSpots(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return parkingSpot;
    }

    @Override
    public ParkingSpots updateParkingSpot(ParkingSpots parkingSpot) {

        String sql = "update parking_spots set car_id = ?, is_available = ? where spot_number = ?;";

        try {
            int numberOfRowsAffected = jdbcTemplate.update(sql, parkingSpot.getCarId(),
                    parkingSpot.isAvailable());
            if (numberOfRowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            int spot_number = parkingSpot.getSpotNumber();

            return getParkingSpotBySpotNumber(spot_number);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private ParkingSpots mapRowToParkingSpots(SqlRowSet results) {
        ParkingSpots parkingSpot = new ParkingSpots();

        parkingSpot.setSpotNumber(results.getInt("spot_number"));

        int parkingSpotCarId = results.getInt("car_id");
        Cars carId = carsDao.getCarByCarId(parkingSpotCarId);
        parkingSpot.setCarId(carId);

        parkingSpot.setAvailable(results.getBoolean("is_available"));

        return parkingSpot;
    }
}

