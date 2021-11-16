package ssau.kuznetsov.autotests.models;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "foreign_passport")
@Entity
public class ForeignPassport {
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
    private IssuingDivision foreignPassportIssuingDivision;

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public IssuingDivision getForeignPassportIssuingDivision() {
        return foreignPassportIssuingDivision;
    }

    public void setForeignPassportIssuingDivision(IssuingDivision issuingDivision) {
        this.foreignPassportIssuingDivision = issuingDivision;
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
}
