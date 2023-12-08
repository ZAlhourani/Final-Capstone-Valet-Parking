package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Patrons;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcPatronsDao implements PatronsDao{

    private final JdbcTemplate jdbcTemplate;

    private final UserDao userDao;

    public JdbcPatronsDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
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

        Patrons patron = new Patrons();

        String sql = "select * from patrons where patron_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patronId);

            if (results.next()) {
                patron = mapRowToPatrons(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return patron;
    }

    @Override
    public Patrons createNewPatron(Patrons newPatron) {

        String sql = "insert into patrons (user_id, name, phone_number " +
                "values (?,?,?) returning patron_id";
        try {
            int patronId = jdbcTemplate.queryForObject(sql, Integer.class, newPatron.getUserId(),
                    newPatron.getName(), newPatron.getPhoneNumber());

            return getPatronById(patronId);

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


    }

    @Override
    public Patrons updatePatron(Patrons patron) {

        String sql = "update patrons set user_id = ?, name = ?, phone_number = ? where patron_id = ?;";

        try {
            int numberOfRowsAffected = jdbcTemplate.update(sql, patron.getUserId(), patron.getName(),
                    patron.getPhoneNumber());

            if(numberOfRowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }

            int patronId = patron.getPatronId();
            return getPatronById(patronId);

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }

    private Patrons mapRowToPatrons(SqlRowSet results){

        Patrons patron = new Patrons();

        patron.setPatronId(results.getInt("patron_id"));

        int patronUserId = results.getInt("user_id");
        User userId = userDao.getUserById(patronUserId);
        patron.setUserId(userId);

        patron.setName(results.getString("name"));
        patron.setPhoneNumber(results.getString("phone_number"));

        return patron;
    }
}
