package com.spring.backend.repository;

import com.spring.backend.model.Userweeklyscore;
import com.spring.backend.model.UserweeklyscoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserweeklyscoreRepository extends JpaRepository<Userweeklyscore, UserweeklyscoreId> {
}
