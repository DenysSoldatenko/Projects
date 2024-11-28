package com.example.securitysystem.repositories;

import com.example.securitysystem.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link User} entities.
 *
 * <p>This interface provides methods for querying and modifying user data in the database.</p>
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Finds a user by their email.
   *
   * @param email The email of the user to be found.
   * @return An {@link Optional} containing the user if found, or empty if not.
   */
  Optional<User> findByEmail(String email);

  /**
   * Enables a user account by setting the {@code enabled} field to {@code true}.
   *
   * @param email The email of the user whose account will be enabled.
   */
  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.enabled = true WHERE u.email = :email")
  void enableAppUser(String email);
}
