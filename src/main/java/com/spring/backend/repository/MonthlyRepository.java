package com.spring.backend.repository;

import com.spring.backend.model.Monthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyRepository extends JpaRepository<Monthly, Long> {
}
