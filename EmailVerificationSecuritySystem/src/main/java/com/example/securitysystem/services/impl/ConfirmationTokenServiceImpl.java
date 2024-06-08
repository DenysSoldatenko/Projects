package com.example.securitysystem.services.impl;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.repositories.ConfirmationTokenRepository;
import com.example.securitysystem.services.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ConfirmationTokenService} interface.
 *
 * <p>This service manages operations related to {@link ConfirmationToken} entities, such as
 * saving tokens, retrieving them by their value, finding valid tokens, and confirming tokens.</p>
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  @Override
  public void saveToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  @Override
  public Optional<ConfirmationToken> findByToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  @Override
  public Optional<ConfirmationToken> findValidToken(User appUser) {
    return confirmationTokenRepository.findFirstByUserAndConfirmedAtIsNotNull(appUser);
  }

  @Override
  public void confirmToken(String token) {
    confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}