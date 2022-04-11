package com.spring.springhelloworld.repository;

import com.spring.springhelloworld.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //all CRUD database methods
}
