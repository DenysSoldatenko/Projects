package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String name);
}