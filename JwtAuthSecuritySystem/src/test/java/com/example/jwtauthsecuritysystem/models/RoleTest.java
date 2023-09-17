package com.example.jwtauthsecuritysystem.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoleTest {

  @Test
  void testEnumValues() {
    assertEquals("USER", Role.USER.name());
    assertEquals("ADMIN", Role.ADMIN.name());
  }

  @Test
  void testEnumOrdinals() {
    assertEquals(0, Role.USER.ordinal());
    assertEquals(1, Role.ADMIN.ordinal());
  }

  @Test
  void testEnumToString() {
    assertEquals("USER", Role.USER.toString());
    assertEquals("ADMIN", Role.ADMIN.toString());
  }

  @Test
  void testEnumValueOf() {
    assertEquals(Role.USER, Role.valueOf("USER"));
    assertEquals(Role.ADMIN, Role.valueOf("ADMIN"));
  }
}
