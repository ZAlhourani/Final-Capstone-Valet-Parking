package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Patrons;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPatronsDao implements PatronsDao{

    private final JdbcTemplate jdbcTemplate;

    private final UserDao userDao;

    public JdbcPatronsDao(JdbcTemplate jdbcTemplate, PatronsDao patronsDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public List<Patrons> getAllPatron() {

        List<Patrons> allPatrons = new ArrayList<>();

        String sql = "select * from patrons;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Patrons patron = mapRowToPatrons(results);
                allPatrons.add(patron);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return allPatrons;
    }

    @Override
    public Patrons getPatronById(int patronId) {
        return null;
    }

    @Override
    public Patrons createNewPatron(Patrons newPatron) {
        return null;
    }

    @Override
    public Patrons updatePatron(Patrons patron) {
        return null;
    }

    private Patrons mapRowToPatrons(SqlRowSet results){

        Patrons patron = new Patrons();

        patron.setPatronId(results.getInt("patron_id"));

        int patronUserId = results.getInt("user_id");
        User userId = userDao.getUserById(patronUserId);
        patron.setUser(userId);

        patron.setName(results.getString("name"));
        patron.setPhoneNumber(results.getString("phone_number"));

        return patron;
    }
}
