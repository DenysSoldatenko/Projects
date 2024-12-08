package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Role entities in the database.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
   * Finds a {@link Role} entity by its name.
   *
   * @param name The name of the role.
   * @return The {@link Role} entity with the specified name, or null if no such role exists.
   */
  Role findByName(String name);
}