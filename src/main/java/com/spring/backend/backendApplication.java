package com.spring.backend;

import com.spring.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class backendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(backendApplication.class, args);
    }

    @Autowired
    private UserRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        /*Employee employee = new Employee();
        employee.setName("Adam");
        employee.setAge(19);
        employeeRepository.save(employee);*/
    }
}
