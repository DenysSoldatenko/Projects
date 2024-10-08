package com.example.jwtsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Unit tests for the JwtUser class.
 */
class JwtUserTest {

  @Test
  void userDetailsMethodsShouldWork() {
    Collection<SimpleGrantedAuthority> authorities = Collections.singleton(
        new SimpleGrantedAuthority("ROLE_USER")
    );
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .username("testUser@gamil.com")
        .firstName("John")
        .lastName("Doe")
        .password("password")
        .enabled(true)
        .lastPasswordResetDate(new Date())
        .authorities(authorities)
        .build();

    assertTrue(jwtUser.isAccountNonExpired());
    assertTrue(jwtUser.isAccountNonLocked());
    assertTrue(jwtUser.isCredentialsNonExpired());
    assertTrue(jwtUser.isEnabled());
  }

  @Test
  void getAuthoritiesShouldWork() {
    Collection<SimpleGrantedAuthority> authorities = Collections.singleton(
        new SimpleGrantedAuthority("ROLE_USER")
    );
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .username("testUser")
        .password("password")
        .authorities(authorities)
        .build();

    Collection<? extends GrantedAuthority> userAuthorities = jwtUser.getAuthorities();
    assertNotNull(userAuthorities);
    assertFalse(userAuthorities.isEmpty());
    assertEquals("ROLE_USER", userAuthorities.iterator().next().getAuthority());
  }

  @Test
  void getUsernameShouldReturnUsername() {
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .username("testUser")
        .password("password")
        .build();

    String username = jwtUser.getUsername();
    assertEquals("testUser", username);
  }

  @Test
  void idShouldReturnId() {
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .username("testUser")
        .password("password")
        .build();

    Long id = jwtUser.getId();
    assertEquals(1L, id);
  }

  @Test
  void firstNameShouldReturnFirstName() {
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .firstName("John")
        .password("password")
        .build();

    String firstName = jwtUser.getFirstName();
    assertEquals("John", firstName);
  }

  @Test
  void lastNameShouldReturnLastName() {
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .lastName("Doe")
        .password("password")
        .build();

    String lastName = jwtUser.getLastName();
    assertEquals("Doe", lastName);
  }

  @Test
  void passwordShouldReturnPassword() {
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .password("password")
        .build();

    String password = jwtUser.getPassword();
    assertEquals("password", password);
  }

  @Test
  void lastPasswordResetDateShouldReturnDate() {
    Date date = new Date();
    JwtUser jwtUser = JwtUser.builder()
        .id(1L)
        .lastPasswordResetDate(date)
        .password("password")
        .build();

    Date resetDate = jwtUser.getLastPasswordResetDate();
    assertEquals(date, resetDate);
  }

  @Test
  void equalsAndHashcodeShouldWork() {
    JwtUser user1 = JwtUser.builder()
        .id(1L)
        .username("testUser")
        .password("password")
        .build();

    JwtUser user2 = JwtUser.builder()
        .id(1L)
        .username("testUser")
        .password("password")
        .build();

    JwtUser user3 = JwtUser.builder()
        .id(2L)
        .username("anotherUser")
        .password("password")
        .build();

    assertEquals(user1, user2);
    assertNotEquals(user1, user3);

    assertEquals(user1.hashCode(), user2.hashCode());
    assertNotEquals(user1.hashCode(), user3.hashCode());
  }
}
