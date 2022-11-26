package ssau.kuznetsov.webquizengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssau.kuznetsov.webquizengine.models.Quiz;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/game")
public class GameController {

    private final QuizRepository quizRepo;
    //@Value("${spring.profiles.active}")
    private String profile = "dev";

    @Autowired
    public GameController(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal QuizUser user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            int startPageNumber = 1;
            Pageable pageable = PageRequest.of(startPageNumber, 1);
            Page<Quiz> firstQuizPage = quizRepo.findAll(pageable);

            data.put("currPageNumber", startPageNumber);
            data.put("currPage", firstQuizPage.getContent().get(0));
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "game";
    }
}
