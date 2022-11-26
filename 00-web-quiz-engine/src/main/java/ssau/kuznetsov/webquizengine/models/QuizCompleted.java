package ssau.kuznetsov.webquizengine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_completed")
public class QuizCompleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;
    @Column(name = "is_right", nullable = false)
    private boolean right;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Quiz quiz;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    private QuizUser quizUser;

    public QuizCompleted() {
    }

    public QuizCompleted(long id, LocalDateTime completedAt, boolean isRight, Quiz quiz) {
        this.id = id;
        this.completedAt = completedAt;
        this.right = isRight;
        this.quiz = quiz;
        this.quizUser = quiz.getUser();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean isRight) {
        this.right = isRight;
    }

    private long getQuizId() {
        return quiz.getId();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @JsonIgnore
    public QuizUser getUser() {
        return quizUser;
    }

    public void setUser(QuizUser author) {
        this.quizUser = author;
    }
}
