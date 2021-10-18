package ssau.kuznetsov.autotests.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.autotests.dto.PassportResponse;
import ssau.kuznetsov.autotests.model.Passport;
import ssau.kuznetsov.autotests.repository.PassportRepository;

@RestController
@RequestMapping("/api")
public class PassportRestcontroller {

    @Autowired
    private PassportRepository passportRepo;

    @GetMapping(path = "/passport/{serialNumber}")
    public ResponseEntity passportBySerialNumber(
            @PathVariable("serialNumber") long serialNumber) {

        Passport passport = passportRepo.findBySerialNumber(serialNumber);

        if (passport == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(new PassportResponse(passport), HttpStatus.OK);
        }
    }
}
