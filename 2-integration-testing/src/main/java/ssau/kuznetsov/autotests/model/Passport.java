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

    public IssuingDivision getPassportIssuingDivision() {
        return passportIssuingDivision;
    }

    public void setPassportIssuingDivision(IssuingDivision issuingDivision) {
        this.passportIssuingDivision = issuingDivision;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long code) {
        this.serialNumber = code;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
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
}
