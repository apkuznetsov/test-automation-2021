package ssau.kuznetsov.autotests.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.autotests.models.IssuingDivision;

@Repository
public interface IssuingDivisionRepo extends JpaRepository<IssuingDivision, Long> {
}
