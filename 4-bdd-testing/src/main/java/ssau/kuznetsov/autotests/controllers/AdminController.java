package ssau.kuznetsov.autotests.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.autotests.repos.CitizenRepo;
import ssau.kuznetsov.autotests.repos.PassportRepo;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private PassportRepo passRep;
    @Autowired
    private CitizenRepo citRep;

    @GetMapping("truncate/passport")
    public void truncatePassport() {
        passRep.deleteAll();
    }

    @GetMapping("truncate/citizen")
    public void truncateCitizen() {
        citRep.deleteAll();
    }

    @GetMapping("truncate/database")
    public void truncateDatabase() {
        passRep.deleteAll();
        citRep.deleteAll();
    }
}
