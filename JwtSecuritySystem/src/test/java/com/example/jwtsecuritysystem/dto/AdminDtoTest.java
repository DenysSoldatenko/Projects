package com.example.jwtsecuritysystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AdminDto class.
 */
public class AdminDtoTest {
  private AdminDto adminDto;

  @BeforeEach
  void setUp() {
    adminDto = new AdminDto();
  }

  @Test
  void testId() {
    adminDto.setId(1L);
    assertEquals(1L, adminDto.getId());
  }

  @Test
  void testUsername() {
    adminDto.setUsername("admin_user");
    assertEquals("admin_user", adminDto.getUsername());
  }

  @Test
  void testFirstName() {
    adminDto.setFirstName("John");
    assertEquals("John", adminDto.getFirstName());
  }

  @Test
  void testLastName() {
    adminDto.setLastName("Doe");
    assertEquals("Doe", adminDto.getLastName());
  }

  @Test
  void testEmail() {
    adminDto.setEmail("john.doe@example.com");
    assertEquals("john.doe@example.com", adminDto.getEmail());
  }

  @Test
  void testStatus() {
    adminDto.setStatus("Active");
    assertEquals("Active", adminDto.getStatus());
  }

  @Test
  void testEquals() {
    AdminDto adminDto1 = new AdminDto();
    adminDto1.setId(1L);

    AdminDto adminDto2 = new AdminDto();
    adminDto2.setId(1L);

    AdminDto adminDto3 = new AdminDto();
    adminDto3.setId(2L);

    assertEquals(adminDto1, adminDto2); // Should be equal
    assertNotEquals(adminDto1, adminDto3); // Should not be equal
  }

  @Test
  void testHashCode() {
    adminDto.setId(1L);
    AdminDto adminDto1 = new AdminDto();
    adminDto1.setId(1L);

    assertEquals(adminDto.hashCode(), adminDto1.hashCode());
  }
}
