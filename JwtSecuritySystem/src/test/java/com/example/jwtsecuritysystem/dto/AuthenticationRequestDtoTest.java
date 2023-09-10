package com.example.jwtsecuritysystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AuthenticationRequestDto class.
 */
public class AuthenticationRequestDtoTest {
  private AuthenticationRequestDto authRequestDto;

  @BeforeEach
  void setUp() {
    authRequestDto = new AuthenticationRequestDto("testUser", "testPassword");
  }

  @Test
  void testGetUsername() {
    assertEquals("testUser", authRequestDto.username());
  }

  @Test
  void testGetPassword() {
    assertEquals("testPassword", authRequestDto.password());
  }
}
