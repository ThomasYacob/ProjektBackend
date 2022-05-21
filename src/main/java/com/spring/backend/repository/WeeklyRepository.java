package com.spring.backend.repository;

import com.spring.backend.model.Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyRepository extends JpaRepository<Weekly, Long> {
}
