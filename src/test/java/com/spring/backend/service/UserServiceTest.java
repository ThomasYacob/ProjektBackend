package com.spring.backend.service;

import com.spring.backend.model.UserAnswer;
import com.spring.backend.model.typeOfQuestion;
import com.spring.backend.repository.ScoreboardRepository;
import com.spring.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.backend.model.Role;
import com.spring.backend.model.User;
import com.spring.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Parser;
import java.sql.Date;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {



    @Mock
    private UserRepository userRepository;
    private ScoreboardRepository scoreboardRepository;

    @InjectMocks
    private UserService userService;
    private ScoreBoardService scoreBoardService;

    private User user;

    @BeforeEach
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
        System.out.println(saveUser);
        assertThat(saveUser).isNotNull();
    }


}