package backend.service;

import com.spring.backend.model.Role;
import com.spring.backend.model.User;
import com.spring.backend.repository.ScoreboardRepository;
import com.spring.backend.repository.UserRepository;
import com.spring.backend.service.ScoreBoardService;
import com.spring.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {



    @Mock
    private UserRepository userRepository;
    private ScoreboardRepository scoreboardRepository;

    @InjectMocks
    private UserService userService;
    private ScoreBoardService scoreBoardService;

    private User user;

   /* @BeforeEach
    public void setup(){
        user = User.builder()
                .email("Redve@kth.se")
                .username("Redve")
                .password("Lol")
                .role(Role.Admin)
                .userAnswers(null)
                .build();
    }*/

    /*@DisplayName("Test Add user")
    @Test
    void testAddUser() throws Exception{
        given(userRepository.save(user)).willReturn(user);
        User saveUser = userService.createNewUserWithoutPasswordAndScoreBoard(user);
        System.out.println(userService.getUser("Redve@kth.se"));
        assertThat(saveUser).isNotNull();
    }*/


}