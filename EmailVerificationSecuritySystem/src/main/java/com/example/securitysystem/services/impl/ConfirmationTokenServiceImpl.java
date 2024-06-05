package com.example.securitysystem.services.impl;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;

import com.example.securitysystem.repositories.ConfirmationTokenRepository;
import com.example.securitysystem.services.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing confirmation tokens and token-related operations.
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