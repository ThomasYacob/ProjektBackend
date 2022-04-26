package com.spring.backend.repository;

import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WeeklyRepository extends JpaRepository<Weekly,Integer> {
}
