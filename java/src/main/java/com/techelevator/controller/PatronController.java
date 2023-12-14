package com.techelevator.controller;

import com.techelevator.dao.PatronsDao;
import com.techelevator.model.Patrons;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class PatronController {

    private final PatronsDao patronsDao;

    public PatronController(PatronsDao patronsDao) {
        this.patronsDao = patronsDao;
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/patrons")
    public List<Patrons> getAllPatronsList(){
        return patronsDao.getAllPatron();
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/patrons/{patronId}")
    public Patrons getPatronById (@PathVariable int patronId) {

        Patrons result = patronsDao.getPatronById(patronId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No patron with that id.");
        } else {
            return result;
        }
    }

    @GetMapping("/patrons/user/{userId}")
    public Patrons getPatronIdByUserId (@PathVariable int userId) {

        Patrons result = patronsDao.getPatronIdByUserId(userId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No patron with that id.");
        } else {
            return result;
        }
    }



    @PreAuthorize("hasRole('ROLE_VALET')")
    @GetMapping("/patrons/phone/{phoneNumber}")
    public Patrons getPatronByPhoneNumber (@PathVariable String phoneNumber) {

        Patrons result = patronsDao.getPatronByPhoneNumber(phoneNumber);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No patron with that phone number.");
        } else {
            return result;
        }
    }


    @PreAuthorize("hasRole('ROLE_VALET')")
    @PostMapping("/patrons")
    public Patrons createNewPatron (@RequestBody Patrons newPatron) {
        return patronsDao.createNewPatron(newPatron);
    }

    @PreAuthorize("hasRole('ROLE_VALET')")
    @PutMapping("/patrons/{patronId}")
    public Patrons updatePatron (@PathVariable int patronId, @RequestBody Patrons updatedPatron) {
        updatedPatron.setPatronId(patronId);

        return patronsDao.updatePatron(updatedPatron);
    }

}
