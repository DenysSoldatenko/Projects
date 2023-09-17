package com.example.jwtauthsecuritysystem.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

  @Test
  void testNoArgsConstructor() {
    User user = new User();
    assertNotNull(user);
  }

  @Test
  void testUserFields() {
    User user = User.builder()
    .id(1)
    .firstname("John")
    .lastname("Doe")
    .email("john.doe@example.com")
    .password("password123")
    .role(Role.USER)
    .build();

    assertEquals(1, user.getId());
    assertEquals("John", user.getFirstname());
    assertEquals("Doe", user.getLastname());
    assertEquals("john.doe@example.com", user.getEmail());
    assertEquals("password123", user.getPassword());
    assertEquals(Role.USER, user.getRole());
  }

  @Test
  void testUserAuthorities() {
    User user = User.builder()
    .id(1)
    .firstname("John")
    .lastname("Doe")
    .email("john.doe@example.com")
    .password("password123")
    .role(Role.USER)
    .build();

    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

    // Check if the user has the correct authority
    assertEquals(1, authorities.size());
    assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
  }

  @Test
  void testUserDetailsMethods() {
    User user = User.builder()
    .id(1)
    .firstname("John")
    .lastname("Doe")
    .email("john.doe@example.com")
    .password("password123")
    .role(Role.USER)
    .build();

    // Check UserDetails methods
    assertEquals("john.doe@example.com", user.getUsername());
    assertEquals("password123", user.getPassword());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isCredentialsNonExpired());
    assertTrue(user.isEnabled());
  }

  @Test
  void testUserToString() {
    User user = User.builder()
    .id(1)
    .firstname("John")
    .lastname("Doe")
    .email("john.doe@example.com")
    .password("password123")
    .role(Role.USER)
    .build();

    String expectedToString = "User(id=1, firstname=John, lastname=Doe, email=john.doe@example.com, password=password123, role=USER)";
    assertEquals(expectedToString, user.toString());
  }
}
