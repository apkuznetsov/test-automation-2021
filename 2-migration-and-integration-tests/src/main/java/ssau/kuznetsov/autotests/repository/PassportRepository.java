package ssau.kuznetsov.autotests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.autotests.model.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    Passport findBySerialNumber(long serialNumber);
}
