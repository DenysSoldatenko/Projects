package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Role entities in the database.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}