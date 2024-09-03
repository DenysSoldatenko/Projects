package com.example.jwtauthsecuritysystem.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RegisterRequestTest {

  @Test
  void testRegisterRequestCreation() {
    RegisterRequest registerRequest = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );

    assertNotNull(registerRequest);
    assertEquals("John", registerRequest.firstname());
    assertEquals("Doe", registerRequest.lastname());
    assertEquals("john.doe@example.com", registerRequest.email());
    assertEquals("password123", registerRequest.password());
  }

  @Test
  void testRegisterRequestEquality() {
    RegisterRequest request1 = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );
    RegisterRequest request2 = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );

    assertEquals(request1, request2);
  }

  @Test
  void testRegisterRequestHashCode() {
    RegisterRequest request1 = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );
    RegisterRequest request2 = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );
    assertEquals(request1.hashCode(), request2.hashCode());
  }

  @Test
  void testRegisterRequestToString() {
    RegisterRequest registerRequest = new RegisterRequest(
        "John",
        "Doe",
        "john.doe@example.com",
        "password123"
    );

    String expectedToString = "RegisterRequest[firstname=John, lastname=Doe, "
        + "email=john.doe@example.com, password=password123]";
    assertEquals(expectedToString, registerRequest.toString());
  }
}
