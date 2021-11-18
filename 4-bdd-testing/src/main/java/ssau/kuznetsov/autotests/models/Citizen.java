package ssau.kuznetsov.autotests.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Table(name = "citizen")
@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "patronymic", nullable = false)
    private String patronymic;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "birth_fed_place", nullable = false)
    private String birthFedPlace;
    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Passport> passports;

    public Citizen() {
    }

    public Citizen(long id, String surname, String name, String patronymic,
                   String birthDate, String birthFedPlace, String birthPlace) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = Date.valueOf(birthDate);
        this.birthFedPlace = birthFedPlace;
        this.birthPlace = birthPlace;
    }

    public List<Passport> getPassports() {
        return passports;
    }

    public void setPassports(List<Passport> passports) {
        this.passports = passports;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthFedPlace() {
        return birthFedPlace;
    }

    public void setBirthFedPlace(String birthFedPlace) {
        this.birthFedPlace = birthFedPlace;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long code) {
        this.id = code;
    }
}
