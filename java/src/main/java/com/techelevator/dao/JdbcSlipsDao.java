package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Cars;
import com.techelevator.model.Patrons;
import com.techelevator.model.Slips;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSlipsDao implements SlipsDao {

    private final JdbcTemplate jdbcTemplate;
    private final PatronsDao patronsDao;

    private final CarsDao carsDao;

    public JdbcSlipsDao(JdbcTemplate jdbcTemplate, PatronsDao patronsDao, CarsDao carsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.patronsDao = patronsDao;
        this.carsDao = carsDao;
    }

    @Override
    public List<Slips> getAllSlips() {

        List<Slips> allSlips = new ArrayList<>();

        String sql = "select * from slips;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Slips slip = mapRowToSlips(results);
                allSlips.add(slip);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allSlips;
    }

    @Override
    public Slips getSlipBySlipNumber(int slipNumber) {

        Slips slip = new Slips();
        String sql = "select * from slips where slip_number =?;";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, slipNumber);

            if (results.next()) {
                slip = mapRowToSlips(results);
            }

        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return slip;

    }

    @Override
    public List<Slips> getSlipByPatronId(int patronId) {

        List<Slips> slip = new ArrayList<Slips>();

        String sql = "select * from slips where patron_id =?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patronId);

            while (results.next()) {
                slip.add( mapRowToSlips(results)) ;
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return slip;
    }


    @Override
    public Slips getSlipByCarId(int carId) {

        Slips slip = new Slips();

        String sql = "select * from slips where car_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, carId);

            if (results.next()) {
                slip = mapRowToSlips(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return slip;
    }

    @Override
    public Slips createNewSlip(Slips newSlip) {

        String sql = "insert into slips (patron_id, car_id, arrival_time, departure_time, hourly_price, total) " +
                "values (?,?,?,?,?,?) returning slip_number;";

        try {
            int slipNumber = jdbcTemplate.queryForObject(sql,
                    Integer.class,
                    newSlip.getPatronId().getPatronId(),
                    newSlip.getCarId().getCarId(),
                    newSlip.getArrivalTime(),
                    newSlip.getDepartureTime(),
                    newSlip.getHourlyPrice(),
                    newSlip.getTotal());

            return getSlipBySlipNumber(slipNumber);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Slips updateSlip(Slips slip) {

        String sql = "update slips set patron_id = ?, car_id = ?, arrival_time = ?, " +
                "departure_time =?, hourly_price = ?, total = ? where slip_number = ?;";

        try{
            int numberOfRowsAffected = jdbcTemplate.update(sql, slip.getPatronId(), slip.getCarId(),
                    slip.getArrivalTime(), slip.getDepartureTime(), slip.getHourlyPrice(), slip.getTotal());

            if(numberOfRowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            int slipNumber = slip.getSlipNumber();

            return getSlipBySlipNumber(slipNumber);

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private Slips mapRowToSlips(SqlRowSet results) {
        Slips slip = new Slips();

        slip.setSlipNumber(results.getInt("slip_number"));

        int slipPatronId = results.getInt("patron_id");
        Patrons patronId = patronsDao.getPatronById(slipPatronId);
        slip.setPatronId(patronId);

        int slipCarId = results.getInt("car_id");
        Cars carId = carsDao.getCarByCarId(slipCarId);
        slip.setCarId(carId);

        slip.setArrivalTime(results.getTimestamp("arrival_time").toLocalDateTime());
        if (results.getTimestamp("departure_time") != null) {
            slip.setDepartureTime(results.getTimestamp("departure_time").toLocalDateTime());
        }
        slip.setHourlyPrice(results.getDouble("hourly_price"));
        slip.setTotal(results.getDouble("total"));

        return slip;
    }
    }