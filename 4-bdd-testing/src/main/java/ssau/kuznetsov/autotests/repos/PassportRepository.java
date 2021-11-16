package ssau.kuznetsov.autotests.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.autotests.models.Passport;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    Passport findBySerialNumber(long serialNumber);

    List<Passport> findAll();

    List<Passport> findAllByCitizenIdIn(List<Long> citizenIdsList);

    @Query(value = "SELECT create_valid_passport_view()", nativeQuery = true)
    boolean callCreateValidPassportView();
}
