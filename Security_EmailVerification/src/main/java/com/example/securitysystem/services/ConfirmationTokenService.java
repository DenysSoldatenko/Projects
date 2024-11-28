package com.example.securitysystem.services;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.util.Optional;

/**
 * Service interface for managing {@link ConfirmationToken} entities.
 *
 * <p>This interface defines methods for handling the lifecycle of confirmation tokens.
 * It includes operations for saving tokens, retrieving tokens by their value,
 * validating tokens, and confirming tokens associated with {@link User} entities.</p>
 */
public interface ConfirmationTokenService {

  /**
   * Saves a new confirmation token to the database.
   *
   * @param token the {@link ConfirmationToken} entity to save.
   */
  void saveToken(ConfirmationToken token);

  /**
   * Finds a confirmation token by its token string.
   *
   * @param token the token string.
   * @return an Optional containing the {@link ConfirmationToken} if found, otherwise empty.
   */
  Optional<ConfirmationToken> findByToken(String token);

  /**
   * Finds a valid confirmation token associated with a user.
   * A valid token is one that has not been confirmed yet.
   *
   * @param appUser the {@link User} entity whose confirmation token is being searched for.
   * @return an Optional containing the valid {@link ConfirmationToken} if found, otherwise empty.
   */
  Optional<ConfirmationToken> findValidToken(User appUser);

  /**
   * Confirms a confirmation token.
   * This marks the token as confirmed and typically triggers
   * related user actions like account activation.
   *
   * @param token the token string to confirm.
   */
  void confirmToken(String token);
}
