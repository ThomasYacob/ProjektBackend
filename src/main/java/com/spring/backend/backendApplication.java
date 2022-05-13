package com.spring.backend;

import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.spring.backend.*"})
public class backendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(backendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
