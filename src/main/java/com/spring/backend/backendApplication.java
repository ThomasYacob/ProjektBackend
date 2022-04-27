package com.spring.backend;

import com.spring.backend.model.User;
import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class backendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(backendApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*User user = new User();
        user.setUsername("fenty");
        user.setPassword("fenty123");
        user.setEmail("fenty123@hotmail.com");
        userRepository.save(user);*/
    }

}
