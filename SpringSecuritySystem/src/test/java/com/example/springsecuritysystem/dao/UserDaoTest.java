package com.example.springsecuritysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.springsecuritysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Unit tests for the UserDao class.
 */
class UserDaoTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserDao userDao;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldFindUserByEmail() {
    String email = "john.doe@gmail.com";

    UserDetails mockUserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password("password")
        .authorities("ROLE_ADMIN")
        .build();

    when(userService.findUserByEmail(email)).thenReturn(mockUserDetails);
    UserDetails userDetails = userDao.findUserByEmail(email);

    assertEquals(email, userDetails.getUsername());
    assertEquals(1, userDetails.getAuthorities().size());
    assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());
  }

  @Test
  void shouldThrowUsernameNotFoundExceptionForNonexistentUser() {
    String email = "nonexistent@gmail.com";
    when(userService.findUserByEmail(email)).thenThrow(new UsernameNotFoundException("User not found"));
    assertThrows(UsernameNotFoundException.class, () -> userDao.findUserByEmail(email));
  }
}
