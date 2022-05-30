package com.spring.backend.repository;

import com.spring.backend.model.Daily;
import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface DailyRepository extends JpaRepository<Daily, Long> {
    @Query("select daily FROM Daily daily WHERE daily.date = ?1")
    Daily findCurrentDaily(Date date);
}
