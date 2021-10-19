package ssau.kuznetsov.autotests.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.autotests.dto.PassportResponse;
import ssau.kuznetsov.autotests.model.Citizen;
import ssau.kuznetsov.autotests.model.Passport;
import ssau.kuznetsov.autotests.repository.CitizenRepository;
import ssau.kuznetsov.autotests.repository.PassportRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/passport")
public class PassportRestcontroller {

    @Autowired
    private PassportRepository passportRepo;
    @Autowired
    private CitizenRepository citizenRepo;

    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity passportBySerialNumber(
            @PathVariable("serialNumber") long serialNumber) {

        Passport passport = passportRepo.findBySerialNumber(serialNumber);

        ResponseEntity response;
        if (passport == null) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity(new PassportResponse(passport), HttpStatus.OK);
        }

        return response;
    }

    @GetMapping(path = "/surname/{surname}")
    public ResponseEntity passportsBySurname(
            @PathVariable("surname") String surname) {

        List<Citizen> citizens = citizenRepo.findAllBySurname(surname);
        if (citizens == null || citizens.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<Long> citizenIds = citizens.stream().map(Citizen::getId).collect(Collectors.toList());

        List<Passport> passports = passportRepo.findAllByCitizenIdIn(citizenIds);
        if (passports == null || passports.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            List<PassportResponse> passportResponses = passports.stream()
                    .map(PassportResponse::new).collect(Collectors.toList());
            return new ResponseEntity(passportResponses, HttpStatus.OK);
        }
    }
}
