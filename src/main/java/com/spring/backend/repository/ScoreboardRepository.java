package com.spring.backend.repository;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScoreboardRepository extends JpaRepository<Scoreboard,Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(/*@Param("username")*/ String email);

    @Query("SELECT u FROM Scoreboard u where u.user = ?1")
    Scoreboard findScoreboardByUser(User user);

}
