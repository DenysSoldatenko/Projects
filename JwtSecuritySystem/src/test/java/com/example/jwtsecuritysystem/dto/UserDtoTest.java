package com.example.jwtsecuritysystem.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the UserDto class.
 */
public class UserDtoTest {
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
    userDto.setId(1L);
    userDto.setUsername("testUser");
    userDto.setFirstName("John");
    userDto.setLastName("Doe");
    userDto.setEmail("johndoe@example.com");
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
  void testGetEmail() {
    assertEquals("johndoe@example.com", userDto.getEmail());
  }

  @Test
  void testEquals() {
    UserDto userDto1 = new UserDto();
    userDto1.setId(1L);
    userDto1.setUsername("testUser");
    userDto1.setFirstName("John");
    userDto1.setLastName("Doe");
    userDto1.setEmail("johndoe@example.com");

    UserDto userDto2 = new UserDto();
    userDto2.setId(2L);
    userDto2.setUsername("anotherUser");
    userDto2.setFirstName("Alice");
    userDto2.setLastName("Smith");
    userDto2.setEmail("alicesmith@example.com");

    assertEquals(userDto, userDto1); // Should be equal
    assertNotEquals(userDto, userDto2); // Should not be equal
  }

  @Test
  void testHashCode() {
    UserDto userDto1 = new UserDto();
    userDto1.setId(1L);
    userDto1.setUsername("testUser");
    userDto1.setFirstName("John");
    userDto1.setLastName("Doe");
    userDto1.setEmail("johndoe@example.com");

    assertEquals(userDto.hashCode(), userDto1.hashCode());
  }
}
