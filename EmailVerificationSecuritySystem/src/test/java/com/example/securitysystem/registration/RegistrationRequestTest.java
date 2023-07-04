package com.example.securitysystem.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RegistrationRequest class.
 */
class RegistrationRequestTest {

  @Test
  void testConstructorAndGetterMethods() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");

    assertEquals("John", request.firstName());
    assertEquals("Doe", request.lastName());
    assertEquals("john@example.com", request.email());
    assertEquals("password123", request.password());
  }

  @Test
  void testEquality() {
    RegistrationRequest request1
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");
    RegistrationRequest request2
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");

    assertEquals(request1, request2);
  }

  @Test
  void testToString() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");
    String expectedToString = "RegistrationRequest[firstName=John, lastName=Doe, "
        + "email=john@example.com, password=password123]";

    assertEquals(expectedToString, request.toString());
  }
}
