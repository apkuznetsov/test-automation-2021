package ssau.kuznetsov.webquizengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ssau.kuznetsov.webquizengine.models.Quiz;
import ssau.kuznetsov.webquizengine.models.QuizCompleted;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizCompletedRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizUserRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private QuizUserRepository userRepo;

    @Autowired
    private QuizRepository quizRepo;

    @Autowired
    private QuizCompletedRepository quizCompletedRepo;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<QuizUser> register(@Valid @RequestBody QuizUser newUser) {
        QuizUser foundUser = userRepo.findByEmail(newUser.getEmail());
        if (foundUser == null) {
            newUser.setPassword(
                    bCryptPasswordEncoder.encode(newUser.getPassword())
            );
            userRepo.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/quizzes")
    public ResponseEntity<List<Quiz>> quizzes(@AuthenticationPrincipal QuizUser user) {
        return new ResponseEntity<>(quizRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/quizzes/completed")
    public ResponseEntity<List<QuizCompleted>> quizzesCompleted(@AuthenticationPrincipal QuizUser user) {
        return new ResponseEntity<>(quizCompletedRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/quizzes/page/{page}")
    public ResponseEntity<Quiz> quizzesPageable(@PathVariable int page, @AuthenticationPrincipal QuizUser user) {
        Pageable pageable = PageRequest.of(page, 1);
        List<Quiz> quizAsList = quizRepo.findAll(pageable).getContent();

        if (quizAsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(quizAsList.get(0), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/quiz/{id}")
    public ResponseEntity<Quiz> quiz(@PathVariable long id, @AuthenticationPrincipal QuizUser user) {
        Quiz quiz = quizRepo.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping(value = "/quiz", consumes = "application/json")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal QuizUser user) {
        quiz.setUser(user);
        quizRepo.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PutMapping(value = "/quiz/{id}", consumes = "application/json")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable long id, @Valid @RequestBody Quiz quiz, @AuthenticationPrincipal QuizUser user) {
        quizRepo.findById(id)
                .map(currQuiz -> {
                    currQuiz.setTitle(quiz.getTitle());
                    currQuiz.setQuestion(quiz.getQuestion());
                    currQuiz.setAnswer(quiz.getAnswer());
                    currQuiz.setUser(new QuizUser(user));
                    return quizRepo.save(currQuiz);
                })
                .orElseGet(() -> {
                    quiz.setId(id);
                    return quizRepo.save(quiz);
                });
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    private boolean markQuiz(long id, QuizUser user, boolean isRight, QuizCompleted cq) {
        Quiz quiz = quizRepo.findById(id).orElse(null);

        if (quiz != null) {
            cq.setCompletedAt(LocalDateTime.now());
            cq.setQuiz(quiz);
            cq.setUser(user);
            cq.setRight(isRight);
            quizCompletedRepo.save(cq);

            quiz.getQuizCompleteds().add(cq);
            quizRepo.save(quiz);

            return true;
        }

        return false;
    }

    @GetMapping(path = "/quiz/{id}/mark/right")
    public ResponseEntity<QuizCompleted> markQuizRight(@PathVariable long id, @AuthenticationPrincipal QuizUser user) {
        QuizCompleted cq = new QuizCompleted();
        boolean isOk = markQuiz(id, user, true, cq);
        if (isOk) {
            return new ResponseEntity<>(cq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/quiz/{id}/mark/wrong")
    public ResponseEntity<QuizCompleted> markQuizWrong(@PathVariable long id, @AuthenticationPrincipal QuizUser user) {
        QuizCompleted cq = new QuizCompleted();
        boolean isOk = markQuiz(id, user, false, cq);
        if (isOk) {
            return new ResponseEntity<>(cq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quiz/{id}")
    public ResponseEntity deleteQuiz(@PathVariable long id, @AuthenticationPrincipal QuizUser user) {
        ResponseEntity resp;

        Quiz quiz = quizRepo.findById(id).orElse(null);
        if (quiz != null) {
            if (quiz.getUser().getEmail().equals(user.getEmail())) {
                quizRepo.deleteById(id);
                resp = new ResponseEntity(HttpStatus.NO_CONTENT);
            } else {
                resp = new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        } else {
            resp = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return resp;
    }
}
