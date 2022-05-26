package com.spring.backend.repository;

import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Long> {

    @Query("select weekly FROM Weekly weekly WHERE weekly.date = ?1")
    Weekly findCurrentWeekly(Date date);
}
