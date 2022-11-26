package ssau.kuznetsov.webquizengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.webquizengine.models.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}