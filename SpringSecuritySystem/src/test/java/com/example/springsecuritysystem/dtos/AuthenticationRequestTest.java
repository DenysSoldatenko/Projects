package com.example.springsecuritysystem.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AuthenticationRequest class.
 */
public class AuthenticationRequestTest {

  @Test
  public void testAuthenticationRequestConstructorAndGetters() {
    String email = "test@example.com";
    String password = "testPassword";
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);

    assertEquals(email, authenticationRequest.email());
    assertEquals(password, authenticationRequest.password());
  }

  @Test
  public void testAuthenticationRequestToString() {
    String email = "test@example.com";
    String password = "testPassword";
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);

    assertNotNull(authenticationRequest.toString());
  }
}
