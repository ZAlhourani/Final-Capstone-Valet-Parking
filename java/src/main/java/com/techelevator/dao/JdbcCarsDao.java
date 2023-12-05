package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cars;
import com.techelevator.model.Patrons;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCarsDao implements CarsDao {

    private final JdbcTemplate jdbcTemplate;

    private final PatronsDao patronsDao;

    public JdbcCarsDao(JdbcTemplate jdbcTemplate, PatronsDao patronsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.patronsDao = patronsDao;
    }

    @Override
    public List<Cars> getAllCars() {

        List<Cars> allCars = new ArrayList<>();

        String sql = "select * from cars;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Cars car = mapRowToCars(results);
                allCars.add(car);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allCars;

    }

    @Override
    public Cars getCarByCarId(int carId) {

        Cars car = new Cars();

        String sql = "select * from cars where car_id = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, carId);
            if (rowSet.next()) {
                car = mapRowToCars(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return car;
    }

    @Override
    public Cars getCarByPatronId(int patronId) {
        Cars car = new Cars();
        String sql = "select * from cars where patron_id = ?;";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, patronId);
            if (rowSet.next()) {
                car = mapRowToCars(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return car;
    }

    @Override
    public Cars createNewCar(Cars newCar) {
        Cars car;

        String sql = "insert into cars (make, model, color, license_plate, vin_number, patron_id) " +
                "values (?,?,?,?,?,?) returning car_id;";
        try {
            int carId = jdbcTemplate.queryForObject(sql, Integer.class,
                    newCar.getMake(), newCar.getModel(), newCar.getColor(),
                    newCar.getLicensePlate(), newCar.getVinNumber(), newCar.getPatronId());

            return getCarByCarId(carId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Cars updateCar(Cars car) {

        String sql = "update cars set make = ?, model = ?, color = ?, license_plate = ?, " +
                "vin_number = ?, patron_id = ? where car_id = ?;";

        try {
            int numberOfRowsAffected = jdbcTemplate.update(sql,
                    car.getMake(), car.getModel(), car.getColor(), car.getLicensePlate(),
                    car.getVinNumber(), car.getPatronId());
            if (numberOfRowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            int carId = car.getCarId();

            return getCarByCarId(carId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private Cars mapRowToCars(SqlRowSet results) {
        Cars car = new Cars();
        car.setCarId(results.getInt("car_id"));
        car.setMake(results.getString("make"));
        car.setModel(results.getString("model"));
        car.setColor(results.getString("color"));
        car.setLicensePlate(results.getString("license_plate"));
        car.setVinNumber(results.getString("vin_number"));

        int carPatronId = results.getInt("patron_id");
        Patrons patronId = patronsDao.getPatronById(carPatronId);
        car.setPatronId(patronId);

        return car;
    }
}
