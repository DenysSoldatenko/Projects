package com.example.securitysystem.appuser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the EmailBuilder class.
 */
class AppUserRoleTest {

  @Test
  void testUserEnumValue() {
    AppUserRole userRole = AppUserRole.USER;
    assertEquals("USER", userRole.name());
    assertEquals(0, userRole.ordinal());
  }

  @Test
  void testAdminEnumValue() {
    AppUserRole adminRole = AppUserRole.ADMIN;
    assertEquals("ADMIN", adminRole.name());
    assertEquals(1, adminRole.ordinal());
  }
}
