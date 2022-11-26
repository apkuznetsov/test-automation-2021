package ssau.kuznetsov.webquizengine.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.webquizengine.models.QuizCompleted;

@Repository
public interface QuizCompletedRepository extends JpaRepository<QuizCompleted, Long> {
}