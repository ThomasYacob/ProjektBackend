package com.spring.springhelloworld;

import com.spring.springhelloworld.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringHelloWorldApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringHelloWorldApplication.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        /*Employee employee = new Employee();
        employee.setName("Adam");
        employee.setAge(19);
        employeeRepository.save(employee);*/
    }
}
