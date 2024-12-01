package com.example.jwtauthsecuritysystem.repositories;

import com.example.jwtauthsecuritysystem.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link User} entities.
 *
 * <p>This interface extends {@link JpaRepository} and provides methods to perform CRUD operations
 * on {@link User} entities, including finding users by their email address.</p>
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Finds a {@link User} by their email address.
   *
   * @param email The email address of the user to find.
   * @return An Optional containing the found user, or an empty Optional if no user is found.
   */
  Optional<User> findByEmail(String email);
}
