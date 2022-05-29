package com.spring.backend.repository;

import com.spring.backend.model.Monthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository class is used to access, manage and
 * persist data between the "Monthly" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface MonthlyRepository extends JpaRepository<Monthly, Long> {
}
