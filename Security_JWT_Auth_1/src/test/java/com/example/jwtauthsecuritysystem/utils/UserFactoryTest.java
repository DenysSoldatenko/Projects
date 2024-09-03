package com.example.jwtauthsecuritysystem.utils;

import static com.example.jwtauthsecuritysystem.models.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserFactoryTest {

  @Autowired
  private UserFactory userFactory;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void testCreateUserFromRequest() {
    RegisterRequest registerRequest = new RegisterRequest(
        "John", "Doe", "john@example.com", "password"
    );

    User createdUser = userFactory.createUserFromRequest(registerRequest);

    assertEquals(registerRequest.firstname(), createdUser.getFirstname());
    assertEquals(registerRequest.lastname(), createdUser.getLastname());
    assertEquals(registerRequest.email(), createdUser.getEmail());
    assertEquals(USER, createdUser.getRole());

    assertTrue(passwordEncoder.matches(registerRequest.password(), createdUser.getPassword()));
  }
}
