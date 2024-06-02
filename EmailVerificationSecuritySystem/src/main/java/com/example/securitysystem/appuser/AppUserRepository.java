package com.example.securitysystem.appuser;

import java.util.Optional;

import com.example.securitysystem.entities.User;
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
public interface AppUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE User u SET u.enabled = true WHERE u.email = :email")
  void enableAppUser(String email);
}