package com.example.jwtauthsecuritysystem.services;

import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.Role;
import com.example.jwtauthsecuritysystem.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Factory class for creating User instances from registration requests.
 */
@Component
@RequiredArgsConstructor
public class UserFactory {

  private final PasswordEncoder passwordEncoder;

  /**
   * Creates a new User instance from a registration request.
   *
   * @param request The registration request containing user information.
   * @return A new User instance.
   */
  public User createUserFromRequest(RegisterRequest request) {
    return User.builder()
    .firstname(request.firstname())
    .lastname(request.lastname())
    .email(request.email())
    .password(passwordEncoder.encode(request.password()))
    .role(Role.USER)
    .build();
  }
}
