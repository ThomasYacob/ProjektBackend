/*package com.spring.backend.controller;

import com.spring.backend.controller.UserAnswerController;
import com.spring.backend.model.User;
import com.spring.backend.model.UserAnswer;
import com.spring.backend.model.typeOfQuestion;
import com.spring.backend.repository.UserAnswerRepository;
import com.spring.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class UserAnswerControllerTest {

    @Autowired
    private UserAnswerController userAnswerController;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserRepository userRepository;

    private UserAnswer userAnswer;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testName","test@hotmail.com","test123");
        userAnswer = new UserAnswer(1L,"Test Answer",new Date(1990L), typeOfQuestion.Weekly,2L,"Test Correct",user);
    }

    @AfterEach
    void tearDown() {
        user = null;
        userAnswer = null;
    }

    @Test
    @Transactional
    void getAnswers() {
        userRepository.save(user);
        userAnswerRepository.save(userAnswer);
        Set<UserAnswer> test = userAnswerController.getAnswers(1L);
        assertNotNull(test);
    }

    @Test
    void getAll() {
        userRepository.save(user);
        userAnswerRepository.save(userAnswer);
        List<UserAnswer> list = userAnswerController.getAll();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void setAnswer() {
        userRepository.save(user);
        userAnswerController.setAnswer(1L,userAnswer);
        assertNotNull(userAnswerRepository.findById(1L).get());
    }
}

*/