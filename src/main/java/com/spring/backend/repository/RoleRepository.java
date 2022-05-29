package com.spring.backend.repository;

import com.spring.backend.model.ERole;
import com.spring.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This repository class is used to access, manage and
 * persist data between the "Role" Objects and the database.
 * This class extends JpaRepository, containing the basic CRUD operations
 * and API for pagination and sorting.
 *
 * @authors Thomas Yacob, Redve Ahmed, Zaed Noori
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
