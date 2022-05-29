package com.spring.backend.repository;

import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;

/**
 * This repository class is used to access, manage and
 * persist data between the "Weekly" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Long> {

    @Query("select weekly FROM Weekly weekly WHERE weekly.date = ?1")
    Weekly findCurrentWeekly(Date date);
}
