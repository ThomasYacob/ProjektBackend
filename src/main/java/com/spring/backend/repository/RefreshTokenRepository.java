package com.spring.backend.repository;

import com.spring.backend.model.RefreshToken;
import com.spring.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository class is used to access, manage and
 * persist data between the "RefreshToken" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}