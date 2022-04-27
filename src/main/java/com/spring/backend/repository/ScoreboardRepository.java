package com.spring.backend.repository;

import com.spring.backend.model.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreboardRepository extends JpaRepository<Scoreboard,Integer> {
}
