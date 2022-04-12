package com.spring.backend.repository;

import com.spring.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //all CRUD database methods
}
