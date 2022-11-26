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

    @GetMapping("create/passport/surname/{surname}/name/{name}")
    public void createTestPassportsWithSuchSurnameAndName(
            @PathVariable String surname, @PathVariable String name) {

        Citizen cit1 = new Citizen(99L, "Морозов", "Артём", "Тимофеевич",
                "1957-10-08", "Республика Северная Осетия — Алания", "Гусь-Хрустальный");
        IssuingDivision div1 = new IssuingDivision(999_999L,
                "Отдел по вопросам миграции ОП №4 (Октябрьский район) УМВД России по г. Самаре");
        Passport pass1 = new Passport(9_999_999_999L, "2012-05-17", "2031-09-10");

        cit1 = citRep.saveAndFlush(cit1);
        div1 = divRep.saveAndFlush(div1);

        pass1.setCitizen(cit1);
        pass1.setPassportIssuingDivision(div1);
        passRep.save(pass1);

        Citizen cit2 = new Citizen(98L, surname, name, "Петрович",
                "1960-10-08", "Самарская область", "Гусь-Хрустальный");
        IssuingDivision div2 = new IssuingDivision(999_998L,
                "Отдел по вопросам миграции ОП № 1 (Кировский район) УМВД России по г. Самаре");
        Passport pass2 = new Passport(9_999_999_998L, "2015-05-17", "2030-09-10");

        cit2 = citRep.saveAndFlush(cit2);
        div2 = divRep.saveAndFlush(div2);

        pass2.setCitizen(cit2);
        pass2.setPassportIssuingDivision(div2);
        passRep.save(pass2);

        Citizen cit3 = new Citizen(97L, surname, name, "Павлович",
                "1980-12-08", "Московская область", "Самара");
        IssuingDivision div3 = new IssuingDivision(999_997L,
                "Паспортно-визовый Сервис");
        Passport pass3 = new Passport(9_999_999_997L, "2017-05-17", "2029-09-10");

        cit3 = citRep.saveAndFlush(cit3);
        div3 = divRep.saveAndFlush(div3);

        pass3.setCitizen(cit3);
        pass3.setPassportIssuingDivision(div3);
        passRep.save(pass3);
    }
}
