package com.spring.backend.repository;

import com.spring.backend.model.Usermonthlyscore;
import com.spring.backend.model.UsermonthlyscoreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usermonthlyscorerepository extends JpaRepository<Usermonthlyscore, UsermonthlyscoreId> {
}
