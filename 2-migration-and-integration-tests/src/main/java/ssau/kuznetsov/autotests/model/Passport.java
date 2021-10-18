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
    @Column(name = "citizen_id", nullable = false)
    private Long citizenId;
    @Column(name = "issuing_division_code", nullable = false)
    private Long issuingDivisionCode;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Column(name = "validity_date", nullable = false)
    private Date validityDate;

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long code) {
        this.serialNumber = code;
    }

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public Long getIssuingDivisionCode() {
        return issuingDivisionCode;
    }

    public void setIssuingDivisionCode(Long issuingDivisionCode) {
        this.issuingDivisionCode = issuingDivisionCode;
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
