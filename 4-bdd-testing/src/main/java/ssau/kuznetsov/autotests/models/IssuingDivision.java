package ssau.kuznetsov.autotests.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Table(name = "issuing_division")
@Entity
public class IssuingDivision {
    @Id
    @Column(name = "code", nullable = false)
    private Long code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "passportIssuingDivision", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Passport> passports;

    @OneToMany(mappedBy = "foreignPassportIssuingDivision", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ForeignPassport> foreignPassports;

    public IssuingDivision() {
    }

    public IssuingDivision(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<Passport> getPassports() {
        return passports;
    }

    public void setPassports(List<Passport> passports) {
        this.passports = passports;
    }

    public List<ForeignPassport> getForeignPassports() {
        return foreignPassports;
    }

    public void setForeignPassports(List<ForeignPassport> foreignPassports) {
        this.foreignPassports = foreignPassports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
