package com.spring.backend.repository;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreboardRepository extends JpaRepository<Scoreboard, User> {
}
