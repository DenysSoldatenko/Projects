package com.example.securitysystem.registration.token;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;

import com.example.securitysystem.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing confirmation tokens and token-related operations.
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationToken> getToken(String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public Optional<ConfirmationToken> findNonExpiredToken(User appUser) {
    return confirmationTokenRepository.findFirstByUserAndConfirmedAtIsNotNull(appUser);
  }

  public void setConfirmedAt(String token) {
    confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}