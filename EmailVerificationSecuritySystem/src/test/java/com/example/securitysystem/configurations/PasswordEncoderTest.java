package com.example.securitysystem.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Unit tests for the PasswordEncoder class.
 */
class PasswordEncoderTest {

  @Test
  void testBcryptPasswordEncoderBean() {
    BCryptPasswordEncoder bcryptPasswordEncoder = new PasswordEncoder().bcryptPasswordEncoder();

    assertNotNull(bcryptPasswordEncoder);
  }
}
