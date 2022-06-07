package com.spring.backend;

import com.spring.backend.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@OpenAPIDefinition(info = @Info(title = "Competitive Assignments API", version = "2.6.6",description = "Documentation for the Schemas & REST API methods provided" +
        " by the Server."))
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
