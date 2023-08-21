package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}