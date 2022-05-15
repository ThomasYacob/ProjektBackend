package com.spring.backend;

import com.spring.backend.controller.UserController;
import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckHTTPResponse {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldPassIfStringMatches() {
        assertEquals("Hello World from Spring Boot",
                testRestTemplate.getForObject("http://localhost:8080"  + "/", String.class));
    }

//    @Test
//    public void testFindByUsername() {
//        String username = "hej";
//        User user = userRepository.findByUsername(username);
//        AssertionsForClassTypes.assertThat(user).isNotNull();
//    }
}
