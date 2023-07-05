package com.example.jwtsecuritysystem.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Unit tests for the PasswordEncoder class.
 */
@SpringBootTest
class PasswordEncoderTest {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Test
  void testPasswordEncoderBean() {
    assertNotNull(passwordEncoder);
  }
}
