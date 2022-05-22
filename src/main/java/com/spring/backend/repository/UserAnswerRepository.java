package com.spring.backend.repository;

import com.spring.backend.model.User;
import com.spring.backend.model.UserAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT ua FROM UserAnswer ua WHERE ua.user.id = ?1")
    Set<UserAnswer> findUserAnswerByUsername(Long id);

    @Query("SELECT u from User u WHERE u.id = ?1")
    User findUser(Long id);

}
