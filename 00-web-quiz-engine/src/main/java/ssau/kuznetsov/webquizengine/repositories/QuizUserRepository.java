package ssau.kuznetsov.webquizengine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.kuznetsov.webquizengine.models.QuizUser;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, Long> {
    QuizUser findByEmail(String email);
}