package com.example.securitysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import com.example.securitysystem.entities.User;
import com.example.securitysystem.entities.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Unit tests for the AppUser class.
 */
class AppUserTest {

  @Test
  void testGetAuthorities() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);

    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

    assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
  }

  @Test
  void testIsAccountNonExpired() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);

    assertTrue(user.isAccountNonExpired());
  }

  @Test
  void testIsAccountNonLocked() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);
    user.setLocked(true);

    assertFalse(user.isAccountNonLocked());
  }

  @Test
  void testIsCredentialsNonExpired() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);

    assertTrue(user.isCredentialsNonExpired());
  }

  @Test
  void testIsEnabled() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);
    user.setEnabled(true);

    assertTrue(user.isEnabled());
  }

  @Test
  void testIsUsername() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);

    assertEquals("john@example.com", user.getUsername());
  }

  @Test
  void testIsPassword() {
    User user = new User("John", "Doe", "john@example.com", "password", UserRole.USER);

    assertEquals("password", user.getPassword());
  }

  @Test
  void testIdGetter() {
    User user = new User();
    user.setId(1L);

    assertEquals(1L, user.getId());
  }

  @Test
  void testFirstNameGetter() {
    User user = new User();
    user.setFirstName("John");

    assertEquals("John", user.getFirstName());
  }

  @Test
  void testLastNameGetter() {
    User user = new User();
    user.setLastName("Doe");

    assertEquals("Doe", user.getLastName());
  }

  @Test
  void testEmailGetter() {
    User user = new User();
    user.setEmail("john@example.com");

    assertEquals("john@example.com", user.getEmail());
  }

  @Test
  void testPasswordGetter() {
    User user = new User();
    user.setPassword("password123");

    assertEquals("password123", user.getPassword());
  }

  @Test
  void testAppUserRoleGetter() {
    User user = new User();
    user.setUserRole(UserRole.USER);

    assertEquals(UserRole.USER, user.getUserRole());
  }
}
