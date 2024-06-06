package com.example.securitysystem.services;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;

import java.util.Optional;

public interface ConfirmationTokenService {

  void saveToken(ConfirmationToken token);

  Optional<ConfirmationToken> findByToken(String token);

  Optional<ConfirmationToken> findValidToken(User appUser);

  void confirmToken(String token);
}
