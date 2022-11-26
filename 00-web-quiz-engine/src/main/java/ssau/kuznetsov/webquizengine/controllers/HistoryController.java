package ssau.kuznetsov.webquizengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizCompletedRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final QuizCompletedRepository quizCompletedRepo;
    //@Value("${spring.profiles.active}")
    private String profile = "dev";

    @Autowired
    public HistoryController(QuizCompletedRepository quizCompletedRepo) {
        this.quizCompletedRepo = quizCompletedRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal QuizUser user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("quizzes", quizCompletedRepo.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "history";
    }
}
