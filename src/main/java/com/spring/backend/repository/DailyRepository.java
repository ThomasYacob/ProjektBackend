package com.spring.backend.repository;

import com.spring.backend.model.Daily;
import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Date;

/**
 * This repository class is used to access, manage and
 * persist data between the "Daily" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface DailyRepository extends JpaRepository<Daily, Long> {
    @Query("select daily FROM Daily daily WHERE daily.date = ?1")
    Daily findCurrentDaily(Date date);
}
