package ssau.kuznetsov.autotests.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.autotests.models.Citizen;
import ssau.kuznetsov.autotests.models.IssuingDivision;
import ssau.kuznetsov.autotests.models.Passport;
import ssau.kuznetsov.autotests.repos.CitizenRepo;
import ssau.kuznetsov.autotests.repos.IssuingDivisionRepo;
import ssau.kuznetsov.autotests.repos.PassportRepo;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private PassportRepo passRep;
    @Autowired
    private IssuingDivisionRepo divRep;
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
        divRep.deleteAll();
        citRep.deleteAll();
    }

    @GetMapping("create/passport/{serialNumber}")
    public void createTestPassportWithSuchSerialNumber(@PathVariable long serialNumber) {
        Citizen cit = new Citizen(99L, "Морозов", "Артём", "Тимофеевич",
                "1957-10-08", "Республика Северная Осетия — Алания", "Гусь-Хрустальный");
        IssuingDivision div = new IssuingDivision(999_999L,
                "Отдел по вопросам миграции ОП №4 (Октябрьский район) УМВД России по г. Самаре");
        Passport pass = new Passport(serialNumber, "2012-05-17", "2031-09-10");

        cit = citRep.saveAndFlush(cit);
        div = divRep.saveAndFlush(div);

        pass.setCitizen(cit);
        pass.setPassportIssuingDivision(div);
        passRep.save(pass);
    }
}
