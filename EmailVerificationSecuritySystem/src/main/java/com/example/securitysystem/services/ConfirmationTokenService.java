package com.example.securitysystem.services;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.util.Optional;

/**
 * Service interface for managing {@link ConfirmationToken} entities.
 *
 * <p>This interface provides methods for saving tokens, finding tokens by value,
 * validating tokens, and confirming tokens associated with {@link User} entities.</p>
 */
public interface ConfirmationTokenService {

  void saveToken(ConfirmationToken token);

  Optional<ConfirmationToken> findByToken(String token);

  Optional<ConfirmationToken> findValidToken(User appUser);

  void confirmToken(String token);
}
