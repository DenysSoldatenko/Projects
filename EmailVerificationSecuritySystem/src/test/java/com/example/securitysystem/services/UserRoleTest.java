package com.example.securitysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.securitysystem.entities.UserRole;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the EmailBuilder class.
 */
class UserRoleTest {

  @Test
  void testUserEnumValue() {
    UserRole userRole = UserRole.USER;
    assertEquals("USER", userRole.name());
    assertEquals(0, userRole.ordinal());
  }

  @Test
  void testAdminEnumValue() {
    UserRole adminRole = UserRole.ADMIN;
    assertEquals("ADMIN", adminRole.name());
    assertEquals(1, adminRole.ordinal());
  }
}
