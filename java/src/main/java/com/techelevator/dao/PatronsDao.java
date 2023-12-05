package com.techelevator.dao;

import com.techelevator.model.Patrons;

import java.util.List;

public interface PatronsDao {

    List<Patrons> getAllPatron();
    Patrons getPatronById(int patronId);
    Patrons createNewPatron(Patrons newPatron);
    Patrons updatePatron(Patrons patron);
}
