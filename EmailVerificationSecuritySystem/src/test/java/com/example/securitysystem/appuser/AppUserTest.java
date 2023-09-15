package com.example.securitysystem.appuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Unit tests for the AppUser class.
 */
public class AppUserTest {

  @Test
  public void testGetAuthorities() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

    assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
  }

  @Test
  public void testIsAccountNonExpired() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

    assertTrue(user.isAccountNonExpired());
  }

  @Test
  public void testIsAccountNonLocked() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);
    user.setLocked(true);

    assertFalse(user.isAccountNonLocked());
  }

  @Test
  public void testIsCredentialsNonExpired() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

    assertTrue(user.isCredentialsNonExpired());
  }

  @Test
  public void testIsEnabled() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);
    user.setEnabled(true);

    assertTrue(user.isEnabled());
  }

  @Test
  public void testIsUsername() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

    assertEquals("john@example.com", user.getUsername());
  }

  @Test
  public void testIsPassword() {
    AppUser user = new AppUser("John", "Doe", "john@example.com", "password", AppUserRole.USER);

    assertEquals("password", user.getPassword());
  }

  @Test
  public void testIdGetter() {
    AppUser user = new AppUser();
    user.setId(1L);

    assertEquals(1L, user.getId());
  }

  @Test
  public void testFirstNameGetter() {
    AppUser user = new AppUser();
    user.setFirstName("John");

    assertEquals("John", user.getFirstName());
  }

  @Test
  public void testLastNameGetter() {
    AppUser user = new AppUser();
    user.setLastName("Doe");

    assertEquals("Doe", user.getLastName());
  }

  @Test
  public void testEmailGetter() {
    AppUser user = new AppUser();
    user.setEmail("john@example.com");

    assertEquals("john@example.com", user.getEmail());
  }

  @Test
  public void testPasswordGetter() {
    AppUser user = new AppUser();
    user.setPassword("password123");

    assertEquals("password123", user.getPassword());
  }

  @Test
  public void testAppUserRoleGetter() {
    AppUser user = new AppUser();
    user.setAppUserRole(AppUserRole.USER);

    assertEquals(AppUserRole.USER, user.getAppUserRole());
  }
}
