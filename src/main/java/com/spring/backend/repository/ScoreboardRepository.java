package com.spring.backend.repository;

import com.spring.backend.model.Scoreboard;
import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This repository class is used to access, manage and
 * persist data between the "Scoreboard" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface ScoreboardRepository extends JpaRepository<Scoreboard, Long> {
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findByIdUser(Long id);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM Scoreboard u where u.user = ?1")
    Scoreboard findScoreboardByUser(User user);

    @Modifying
    int deleteByUser(Scoreboard scoreboard);
}
