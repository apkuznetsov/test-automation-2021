package ssau.kuznetsov.webquizengine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    public QuizUser quizUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "question", nullable = false)
    private String question;
    @Column(name = "answer", nullable = false)
    private String answer;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<QuizCompleted> quizCompleteds;

    public Quiz() {
    }

    public Quiz(String title, String question, String answer) {
        this.title = title;
        this.question = question;
        this.answer = answer;
    }

    public Quiz(long id, String title, String question, String answer, QuizUser quizUser) {
        this(title, question, answer);
        this.id = id;
        this.quizUser = new QuizUser(quizUser);
        this.quizCompleteds = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String text) {
        this.question = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuizUser getUser() {
        return quizUser;
    }

    public void setUser(QuizUser quizUser) {
        this.quizUser = quizUser;
    }

    public List<QuizCompleted> getQuizCompleteds() {
        return quizCompleteds;
    }

    public void setQuizCompleteds(List<QuizCompleted> quizCompleteds) {
        this.quizCompleteds = quizCompleteds;
    }
}
