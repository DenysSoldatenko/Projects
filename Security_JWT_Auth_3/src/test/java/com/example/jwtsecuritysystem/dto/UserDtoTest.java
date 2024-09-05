package com.example.jwtsecuritysystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the UserDto class.
 */
class UserDtoTest {
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
    userDto.setId(1L);
    userDto.setUsername("johndoe@example.com");
    userDto.setFirstName("John");
    userDto.setLastName("Doe");
  }

  @Test
  void testGetId() {
    assertEquals(1L, userDto.getId());
  }

  @Test
  void testGetUsername() {
    assertEquals("testUser", userDto.getUsername());
  }

  @Test
  void testGetFirstName() {
    assertEquals("John", userDto.getFirstName());
  }

  @Test
  void testGetLastName() {
    assertEquals("Doe", userDto.getLastName());
  }

  @Test
  void testEquals() {
    UserDto userDto1 = new UserDto();
    userDto1.setId(1L);
    userDto1.setUsername("johndoe@example.com");
    userDto1.setFirstName("John");
    userDto1.setLastName("Doe");

    UserDto userDto2 = new UserDto();
    userDto2.setId(2L);
    userDto2.setUsername("alicesmith@example.com");
    userDto2.setFirstName("Alice");
    userDto2.setLastName("Smith");

    assertEquals(userDto, userDto1); // Should be equal
    assertNotEquals(userDto, userDto2); // Should not be equal
  }

  @Test
  void testHashCode() {
    UserDto userDto1 = new UserDto();
    userDto1.setId(1L);
    userDto1.setUsername("johndoe@example.com");
    userDto1.setFirstName("John");
    userDto1.setLastName("Doe");

    assertEquals(userDto.hashCode(), userDto1.hashCode());
  }
}
