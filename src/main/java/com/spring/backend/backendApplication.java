package com.spring.backend;

import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is used to bootstrap and launch a Spring application.
 * It enables the autoconfiguration feature of a Spring Boot application.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@SpringBootApplication
public class backendApplication implements CommandLineRunner {

    /**
     * Starts the Spring application, runs the embedded servers
     * and loads the Beans.
     */
    public static void main(String[] args) {
        SpringApplication.run(backendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
