package ssau.kuznetsov.autotests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ssau.kuznetsov.autotests.model.Citizen;
import ssau.kuznetsov.autotests.model.Passport;

import java.io.Serializable;
import java.sql.Date;

public class PassportResponse implements Serializable {

    private Passport p;

    public PassportResponse() {
    }

    public PassportResponse(Passport p) {
        this.p = p;
    }

    @JsonProperty("Серия и номер")
    public long getSerialNumber() {
        return p.getSerialNumber();
    }

    @JsonProperty("ФИО")
    public String getFullName() {
        Citizen c = p.getCitizen();
        return c.getSurname() + " " + c.getName() + " " + c.getPatronymic();
    }

    @JsonProperty("Дата рождения")
    public Date getBirthDate() {
        return p.getCitizen().getBirthDate();
    }

    @JsonProperty("Место рождения")
    public String getFullBirthPlace() {
        Citizen c = p.getCitizen();
        return c.getBirthFedPlace() + ", " + c.getBirthPlace();
    }

    @JsonProperty("Паспорт выдан")
    public String getIssuingDivisionName() {
        return p.getPassportIssuingDivision().getName();
    }

    @JsonProperty("Код подразделения")
    public long getIssuingDivisionCode() {
        return p.getPassportIssuingDivision().getCode();
    }

    @JsonProperty("Дата выдачи")
    public Date getIssueDate() {
        return p.getIssueDate();
    }

    public String getSurname() {
        return p.getCitizen().getSurname();
    }

    public String getName() {
        return p.getCitizen().getName();
    }
}
