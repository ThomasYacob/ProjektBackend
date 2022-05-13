package com.spring.backend.repository;

import com.spring.backend.model.User;
import com.spring.backend.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, User> {
}
