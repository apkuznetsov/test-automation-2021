package ssau.kuznetsov.webquizengine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ssau.kuznetsov.webquizengine.controllers.ApiController;
import ssau.kuznetsov.webquizengine.models.Quiz;
import ssau.kuznetsov.webquizengine.models.QuizCompleted;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizCompletedRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizRepository;
import ssau.kuznetsov.webquizengine.repositories.QuizUserRepository;
import ssau.kuznetsov.webquizengine.security.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
public class EndpointsIt {

    @Autowired
    private ApiController apiController;
    @MockBean
    private UserService authService;
    @MockBean
    private QuizUserRepository userRepo;
    @MockBean
    private QuizRepository quizRepo;
    @MockBean
    private QuizCompletedRepository quizCompletedRepo;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private MockitoSession session;

    @BeforeEach
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        session.finishMocking();
    }

    @Test
    public void get_all_quizzes_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q1 = new Quiz(1L, "Английский", "hello", "привет", u);
        Quiz q2 = new Quiz(2L, "Английский", "price", "цена", u);
        Mockito.when(quizRepo.findAll()).thenReturn(Arrays.asList(q1, q2));

        mockMvc.perform(get("/api/quizzes").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(q1, q2))));
    }

    @Test
    public void get_all_completed_quizzes_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q1 = new Quiz(1L, "Английский", "hello", "привет", u);
        Quiz q2 = new Quiz(2L, "Английский", "price", "цена", u);
        QuizCompleted cq1 = new QuizCompleted(1L, LocalDateTime.now(), true, q1);
        QuizCompleted cq2 = new QuizCompleted(2L, LocalDateTime.now(), false, q2);
        Mockito.when(quizCompletedRepo.findAll()).thenReturn(Arrays.asList(cq1, cq2));

        mockMvc.perform(get("/api/quizzes/completed").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(cq1, cq2))));
    }

    @Test
    public void get_quiz_as_page_req_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(q);
        quizzes.add(q);
        Pageable pageable = PageRequest.of(1, 1);
        Page<Quiz> page = new PageImpl<>(quizzes);
        Mockito.when(quizRepo.findAll(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/quizzes/page/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(q)));
    }

    @Test
    public void get_empty_list_of_quizzes_with_status_204() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        List<Quiz> emptyQuizList = new ArrayList<>();
        Pageable pageable = PageRequest.of(1, 1);
        Page<Quiz> page = new PageImpl<>(emptyQuizList);
        Mockito.when(quizRepo.findAll(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/quizzes/page/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void get_quiz_by_id_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.of(q);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(get("/api/quiz/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(q)));
    }

    @Test
    public void get_no_quiz_by_no_such_id_with_status_404() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Optional<Quiz> oq = Optional.empty();
        Mockito.when(quizRepo.findById(Mockito.any())).thenReturn(oq);

        mockMvc.perform(get("/api/quiz/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_quiz_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Mockito.when(quizRepo.save(q)).thenReturn(q);

        mockMvc.perform(post("/api/quiz")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(q))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(q)));
    }

    @Test
    public void update_quiz_found_by_id_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.of(q);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(put("/api/quiz/" + q.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(q))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(q)));
    }

    @Test
    public void update_quiz_not_found_by_id_but_create_new_one_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.empty();
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(put("/api/quiz/" + q.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(q))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(q)));
    }

    @Test
    public void create_completed_quiz_with_status_200() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.of(q);
        QuizCompleted cq = new QuizCompleted(1L, LocalDateTime.now(), true, q);
        q.getQuizCompleteds().add(cq);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);
        Mockito.when(quizCompletedRepo.save(cq)).thenReturn(cq);
        Mockito.when(quizRepo.save(q)).thenReturn(q);

        mockMvc.perform(get("/api/quiz/" + q.getId() + "/mark/right")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.right").value(cq.getRight()))
                .andExpect(jsonPath("$.quiz.id").value(q.getId()))
                .andExpect(jsonPath("$.quiz.title").value(q.getTitle()))
                .andExpect(jsonPath("$.quiz.question").value(q.getQuestion()))
                .andExpect(jsonPath("$.quiz.answer").value(q.getAnswer()))
                .andExpect(jsonPath("$.quiz.user.id").value(q.getUser().getId()))
                .andExpect(jsonPath("$.quiz.user.email").value(q.getUser().getEmail()));
    }

    @Test
    public void cannot_create_completed_quiz_with_status_404_no_such_quiz_id() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.empty();
        QuizCompleted cq = new QuizCompleted(1L, LocalDateTime.now(), true, q);
        q.getQuizCompleteds().add(cq);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(get("/api/quiz/" + q.getId() + "/mark/right")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_quiz_with_status_204() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", u);
        Optional<Quiz> oq = Optional.of(q);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(delete("/api/quiz/" + q.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_quiz_with_status_403() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        QuizUser qu = new QuizUser(2L, "example2@example.com");
        Quiz q = new Quiz(1L, "Английский", "hello", "привет", qu);
        Optional<Quiz> oq = Optional.of(q);
        Mockito.when(quizRepo.findById(q.getId())).thenReturn(oq);

        mockMvc.perform(delete("/api/quiz/" + q.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void delete_quiz_with_status_404() throws Exception {
        QuizUser u = new QuizUser(1L, "example@example.com");
        Optional<Quiz> oq = Optional.empty();
        Mockito.when(quizRepo.findById(Mockito.any())).thenReturn(oq);

        mockMvc.perform(delete("/api/quiz/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user(u)))
                .andExpect(status().isNotFound());
    }
}
