package com.spring.backend.repository;

import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //all CRUD database methods
}
