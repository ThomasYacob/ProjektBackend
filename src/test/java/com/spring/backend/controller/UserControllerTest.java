package com.spring.backend.controller;

import com.spring.backend.model.*;
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
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testName","test@hotmail.com","test123");
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void getAllUser() {
        userRepository.save(user);
        List<User> list = userController.getAllUser();
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    void getUserByMail() {
        userRepository.save(user);
        User test = userController.getUserByMail(1L);
        assertNotNull(test);
    }

    @Test
    void deleteUser() {
        userRepository.save(user);
        userController.deleteUser(1L);
        assertThat(userRepository.findById(1L).isEmpty());
    }

    @Test
    @Transactional
    void updateUser() {
        userRepository.save(user);
        User test = new User("test","test@hotmail.com","test321");
        userController.updateUser(1L,test);
        assertEquals("test321",user.getPassword());
    }

    @Test
    @Transactional
    void changeRoleUser() {
        userRepository.save(user);
        userController.changeRoleUser(1L,new Role(ERole.ContentCreator));
        assertThat(user.getRoles().contains(ERole.ContentCreator));
    }
}