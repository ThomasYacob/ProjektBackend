package com.spring.backend.repository;

import com.spring.backend.model.Monthly;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MonthlyRepository extends JpaRepository<Monthly,Integer> {
}
