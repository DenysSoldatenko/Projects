package com.example.jwtauthsecuritysystem.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

  @Test
  void testAuthenticationResponseCreation() {
    AuthenticationResponse authenticationResponse = new AuthenticationResponse("testToken");

    assertNotNull(authenticationResponse);
    assertEquals("testToken", authenticationResponse.token());
  }

  @Test
  void testAuthenticationResponseEquality() {
    AuthenticationResponse response1 = new AuthenticationResponse("testToken");
    AuthenticationResponse response2 = new AuthenticationResponse("testToken");

    assertEquals(response1, response2);
  }

  @Test
  void testAuthenticationResponseHashCode() {
    AuthenticationResponse response1 = new AuthenticationResponse("testToken");
    AuthenticationResponse response2 = new AuthenticationResponse("testToken");

    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  void testAuthenticationResponseToString() {
    AuthenticationResponse authenticationResponse = new AuthenticationResponse("testToken");

    String expectedToString = "AuthenticationResponse[token=testToken]";
    assertEquals(expectedToString, authenticationResponse.toString());
  }
}
