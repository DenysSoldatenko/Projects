package com.example.jwtsecuritysystem.repositories;

import com.example.jwtsecuritysystem.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Finds a {@link User} by their username and eagerly loads their associated roles.
   *
   * <p>This method retrieves a {@link User} from the database using the provided username.</p>
   *
   * @param name The username of the user to retrieve.
   * @return An {@link Optional} containing the {@link User} if found,
   *     otherwise an empty {@link Optional}.
   */
  @EntityGraph(attributePaths = {"roles"})
  Optional<User> findByUsername(String name);
}