package com.spring.backend.repository;

import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //@Query("Select u from User u WHERE u.username=:username")
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(/*@Param("username")*/ String username);
}
