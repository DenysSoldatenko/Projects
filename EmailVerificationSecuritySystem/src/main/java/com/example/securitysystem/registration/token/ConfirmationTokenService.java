package com.example.securitysystem.registration.token;

import com.example.securitysystem.appuser.AppUser;
import java.time.LocalDateTime;
import java.util.Optional;
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

  public Optional<ConfirmationToken> findNonExpiredToken(AppUser appUser) {
    return confirmationTokenRepository.findFirstByAppUserAndConfirmedAtIsNotNull(appUser);
  }

  public void setConfirmedAt(String token) {
    confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}