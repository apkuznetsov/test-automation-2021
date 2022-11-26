package ssau.kuznetsov.webquizengine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ssau.kuznetsov.webquizengine.models.QuizUser;
import ssau.kuznetsov.webquizengine.repositories.QuizUserRepository;

@Component
@Service
public class UserService implements UserDetailsService {

    @Autowired
    QuizUserRepository quizUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        QuizUser quizUser = quizUserRepository.findByEmail(email);
        if (quizUser == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return quizUser;
    }
}
