package ssau.kuznetsov.autotests.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import ssau.kuznetsov.autotests.model.Citizen;
import ssau.kuznetsov.autotests.model.IssuingDivision;
import ssau.kuznetsov.autotests.model.Passport;

import java.io.Serializable;
import java.sql.Date;

public class PassportResponse implements Serializable {

    private long serialNumber;
    private String fullName;
    private Date birthDate;
    private String fullBirthPlace;
    private String issuingDivisionName;
    private long issuingDivisionCode;
    private Date issueDate;

    public PassportResponse() {
    }

    public PassportResponse(Passport p) {
        Citizen c = p.getCitizen();
        IssuingDivision i = p.getPassportIssuingDivision();

        this.serialNumber = p.getSerialNumber();
        this.fullName = c.getSurname() + " " + c.getName() + " " + c.getPatronymic();
        this.birthDate = c.getBirthDate();
        this.fullBirthPlace = c.getBirthFedPlace() + ", " + c.getBirthPlace();
        this.issuingDivisionName = i.getName();
        this.issuingDivisionCode = i.getCode();
        this.issueDate = p.getIssueDate();
    }

    @JsonProperty("Серия и номер")
    public long getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("ФИО")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("Дата рождения")
    public Date getBirthDate() {
        return birthDate;
    }

    @JsonProperty("Место рождения")
    public String getFullBirthPlace() {
        return fullBirthPlace;
    }

    @JsonProperty("Паспорт выдан")
    public String getIssuingDivisionName() {
        return issuingDivisionName;
    }

    @JsonProperty("Код подразделения")
    public long getIssuingDivisionCode() {
        return issuingDivisionCode;
    }

    @JsonProperty("Дата выдачи")
    public Date getIssueDate() {
        return issueDate;
    }
}
