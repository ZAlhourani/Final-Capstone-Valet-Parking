package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ParkedCarsInfoDto;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcParkedCarsInfoDao implements ParkedCarsInfoDtoDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkedCarsInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ParkedCarsInfoDto> getParkedCarsInfo() {

        List<ParkedCarsInfoDto> parkedCarsInfoDtoList = new ArrayList<>();

        String sql = "select distinct cars.make, cars.model, cars.color, cars.license_plate, cars.vin_number, " +
                "patrons.name, patrons.phone_number, parking_spots.spot_number, slips.total " +
                "from parking_spots " +
                "join cars using (car_id) " +
                "join patrons using (patron_id) " +
                "join slips using (patron_id,car_id) " +
                "where is_available is false and departure_time is null " +
                "order by spot_number ASC;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ParkedCarsInfoDto newList = mapRowCarsInfo(results);
                parkedCarsInfoDtoList.add(newList);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return parkedCarsInfoDtoList;

    }

    private ParkedCarsInfoDto mapRowCarsInfo(SqlRowSet results) {
        ParkedCarsInfoDto parkedCarInfo = new ParkedCarsInfoDto();
        parkedCarInfo.setMake(results.getString("make"));
        parkedCarInfo.setModel(results.getString("model"));
        parkedCarInfo.setColor(results.getString("color"));
        parkedCarInfo.setLicensePlate(results.getString("license_plate"));
        parkedCarInfo.setVinNumber(results.getString("vin_number"));
        parkedCarInfo.setName(results.getString("name"));
        parkedCarInfo.setPhoneNumber(results.getString("phone_number"));
        parkedCarInfo.setSpotNumber(results.getInt("spot_number"));
        parkedCarInfo.setTotal(results.getDouble("total"));

        return parkedCarInfo;
    }
}
