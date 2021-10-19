package ssau.kuznetsov.autotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.autotests.model.Citizen;
import ssau.kuznetsov.autotests.model.Passport;

import java.sql.Date;
import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    List<Citizen> findAllBySurname(String surname);

    List<Citizen> findAllByName(String name);

    List<Citizen> findAllByBirthDate(Date birthDate);
}
