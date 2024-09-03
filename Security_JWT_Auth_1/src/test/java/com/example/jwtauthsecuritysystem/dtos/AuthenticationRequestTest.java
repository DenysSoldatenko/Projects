package com.example.jwtauthsecuritysystem.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AuthenticationRequestTest {

  @Test
  void testAuthenticationRequestCreation() {
    AuthenticationRequest authenticationRequest
        = new AuthenticationRequest("test@example.com", "password123");

    assertNotNull(authenticationRequest);
    assertEquals("test@example.com", authenticationRequest.email());
    assertEquals("password123", authenticationRequest.password());
  }

  @Test
  void testAuthenticationRequestEquality() {
    AuthenticationRequest request1 = new AuthenticationRequest("test@example.com", "password123");
    AuthenticationRequest request2 = new AuthenticationRequest("test@example.com", "password123");

    assertEquals(request1, request2);
  }

  @Test
  void testAuthenticationRequestHashCode() {
    AuthenticationRequest request1 = new AuthenticationRequest("test@example.com", "password123");
    AuthenticationRequest request2 = new AuthenticationRequest("test@example.com", "password123");

    assertEquals(request1.hashCode(), request2.hashCode());
  }

  @Test
  void testAuthenticationRequestToString() {
    AuthenticationRequest authenticationRequest
        = new AuthenticationRequest("test@example.com", "password123");

    String expectedToString = "AuthenticationRequest[email=test@example.com, password=password123]";
    assertEquals(expectedToString, authenticationRequest.toString());
  }
}
