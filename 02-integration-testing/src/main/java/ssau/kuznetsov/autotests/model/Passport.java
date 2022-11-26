package ssau.kuznetsov.autotests.model;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "passport")
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number", nullable = false)
    private Long serialNumber;
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Column(name = "validity_date", nullable = false)
    private Date validityDate;

    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;
    @ManyToOne
    @JoinColumn(name = "issuing_division_code")
    private IssuingDivision passportIssuingDivision;

    public Passport() {
    }

    public Passport(long serialNumber, Date issueDate, Date validityDate) {
        this.serialNumber = serialNumber;
        this.issueDate = issueDate;
        this.validityDate = validityDate;
    }

    public Passport(long serialNumber, String issueDate, String validityDate) {
        this.serialNumber = serialNumber;
        this.issueDate = Date.valueOf(issueDate);
        this.validityDate = Date.valueOf(issueDate);
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long code) {
        this.serialNumber = code;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public IssuingDivision getPassportIssuingDivision() {
        return passportIssuingDivision;
    }

    public void setPassportIssuingDivision(IssuingDivision issuingDivision) {
        this.passportIssuingDivision = issuingDivision;
    }
}
