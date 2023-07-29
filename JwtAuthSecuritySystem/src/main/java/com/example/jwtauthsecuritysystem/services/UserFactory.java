package com.example.jwtauthsecuritysystem.services;

import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.Role;
import com.example.jwtauthsecuritysystem.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

  private final PasswordEncoder passwordEncoder;

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
