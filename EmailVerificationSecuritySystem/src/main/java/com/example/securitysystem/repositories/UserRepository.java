package com.example.securitysystem.repositories;

import com.example.securitysystem.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing AppUser entities.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.enabled = true WHERE u.email = :email")
  void enableAppUser(String email);
}