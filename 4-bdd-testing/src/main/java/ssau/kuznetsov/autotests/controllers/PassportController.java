package ssau.kuznetsov.autotests.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.autotests.dtos.PassportResponse;
import ssau.kuznetsov.autotests.models.Citizen;
import ssau.kuznetsov.autotests.models.Passport;
import ssau.kuznetsov.autotests.repos.CitizenRepo;
import ssau.kuznetsov.autotests.repos.PassportRepo;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/passport/")
public class PassportController {

    @Autowired
    private PassportRepo passRep;
    @Autowired
    private CitizenRepo citRep;

    @GetMapping(path = "all")
    public ResponseEntity allPassports() {

        List<Passport> ps = passRep.findAll();

        ResponseEntity response;
        if (ps == null) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            List<PassportResponse> passportResponses = ps.stream()
                    .map(PassportResponse::new).collect(Collectors.toList());
            response = new ResponseEntity(passportResponses, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping(path = "{serialNumber}")
    public ResponseEntity passportBySerialNumber(
            @PathVariable("serialNumber") long serialNumber) {

        Passport p = passRep.findBySerialNumber(serialNumber);

        ResponseEntity response;
        if (p == null) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity(new PassportResponse(p), HttpStatus.OK);
        }

        return response;
    }

    private ResponseEntity passportsByCitizens(List<Citizen> cs) {
        if (cs == null || cs.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<Long> cIds = cs.stream().map(Citizen::getId).collect(Collectors.toList());

        List<Passport> ps = passRep.findAllByCitizenIdIn(cIds);
        if (ps == null || ps.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            List<PassportResponse> passportResponses = ps.stream()
                    .map(PassportResponse::new).collect(Collectors.toList());
            return new ResponseEntity(passportResponses, HttpStatus.OK);
        }
    }

    @GetMapping(path = "{surname}/{name}/{birthDate}")
    public ResponseEntity passportsBySurnameNameBirthDate(
            @PathVariable("surname") String surname,
            @PathVariable("name") String name,
            @PathVariable("birthDate") Date birthDate) {

        List<Citizen> cs = citRep.findAllBySurnameAndNameAndBirthDate(surname, name, birthDate);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "surname/{surname}")
    public ResponseEntity passportsBySurname(
            @PathVariable("surname") String surname) {

        List<Citizen> cs = citRep.findAllBySurname(surname);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "name/{name}")
    public ResponseEntity passportsByName(
            @PathVariable("name") String name) {

        List<Citizen> cs = citRep.findAllByName(name);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "birth-date/{birthDate}")
    public ResponseEntity passportsByName(
            @PathVariable("birthDate") Date birthDate) {

        List<Citizen> cs = citRep.findAllByBirthDate(birthDate);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "surname/{surname}/name/{name}")
    public ResponseEntity passportsBySurnameAndName(
            @PathVariable("surname") String surname,
            @PathVariable("name") String name) {

        List<Citizen> cs = citRep.findAllBySurnameAndName(surname, name);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "surname/{surname}/birth-date/{birthDate}")
    public ResponseEntity passportsBySurnameAndBirthDate(
            @PathVariable("surname") String surname,
            @PathVariable("birthDate") Date birthDate) {

        List<Citizen> cs = citRep.findAllBySurnameAndBirthDate(surname, birthDate);
        return passportsByCitizens(cs);
    }

    @GetMapping(path = "name/{name}/birth-date/{birthDate}")
    public ResponseEntity passportsByNameAndBirthDate(
            @PathVariable("name") String name,
            @PathVariable("birthDate") Date birthDate) {

        List<Citizen> cs = citRep.findAllByNameAndBirthDate(name, birthDate);
        return passportsByCitizens(cs);
    }

    @GetMapping("citizen/{id}")
    public void deleteCitizen(@PathVariable("id") long id) {
        citRep.deleteById(id);
    }
}
