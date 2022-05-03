package com.spring.backend.repository;

import com.spring.backend.model.UserAnswer;
import com.spring.backend.model.UserAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, UserAnswerId> {
}
