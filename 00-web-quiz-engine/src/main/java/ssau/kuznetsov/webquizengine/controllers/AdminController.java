package ssau.kuznetsov.webquizengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.webquizengine.models.Quiz;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizCompletedRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizUserRepository;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private QuizUserRepository userRepo;
    @Autowired
    private QuizRepository quizRepo;
    @Autowired
    private QuizCompletedRepository quizCompletedRepo;


    @GetMapping("truncate/database")
    public void truncateDatabase(@AuthenticationPrincipal QuizUser admin) {
        quizCompletedRepo.deleteAll();
        quizRepo.deleteAll();
        userRepo.deleteAll();

        QuizUser user = new QuizUser(98L, "example@example.com",
                "$2a$10$5v5rAtrMvEk.0T3FmRGGnevDAImBfZleQHd.0TZDF.C0Gqu1SvUMW");
        userRepo.saveAndFlush(user);
    }

    @GetMapping("create/quizzes")
    public void createTestQuizzes(@AuthenticationPrincipal QuizUser admin) {

        QuizUser user = userRepo.findByEmail("example@example.com");

        if (user == null) {
            user = new QuizUser(98L, "example@example.com",
                    "$2a$10$5v5rAtrMvEk.0T3FmRGGnevDAImBfZleQHd.0TZDF.C0Gqu1SvUMW");
            user = userRepo.saveAndFlush(user);
        }

        Quiz quiz1 = new Quiz(98L, "Английский", "hello", "привет", user);
        quizRepo.save(quiz1);

        Quiz quiz2 = new Quiz(99L, "Английский", "price", "цена", user);
        quizRepo.save(quiz2);
    }
}
