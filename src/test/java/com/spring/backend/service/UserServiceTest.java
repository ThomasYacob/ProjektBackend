package com.spring.backend.service;

import com.spring.backend.model.User;
import com.spring.backend.repository.ScoreboardRepository;
import com.spring.backend.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    }

    @DisplayName("Test Add user")
    @Test
    void testAddUser() throws Exception{
        given(userRepository.save(user)).willReturn(user);
        User saveUser = userService.createNewUserWithoutPasswordAndScoreBoard(user);
        System.out.println(userService.getUser("Redve@kth.se"));
        assertThat(saveUser).isNotNull();
    }*/
}