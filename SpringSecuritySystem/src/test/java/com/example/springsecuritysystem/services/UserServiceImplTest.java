package com.example.springsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.springsecuritysystem.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Unit tests for the UserServiceImpl class.
 */
class UserServiceImplTest {

  private UserService userService;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl();
  }

  @Test
  void shouldFindUserByEmail() {
    String email = "john.doe@gmail.com";

    UserDetails userDetails = userService.findUserByEmail(email);

    assertEquals(email, userDetails.getUsername());
    assertEquals(1, userDetails.getAuthorities().size());
    assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
  }

  @Test
  void shouldThrowUsernameNotFoundExceptionForNonexistentUser() {
    String email = "nonexistent@gmail.com";

    assertThrows(UsernameNotFoundException.class, () -> userService.findUserByEmail(email));
  }
}
