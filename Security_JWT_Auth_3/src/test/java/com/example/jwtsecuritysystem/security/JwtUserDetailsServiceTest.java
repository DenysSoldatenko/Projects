package com.example.jwtsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.Status;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Unit tests for the JwtUserDetailsService class.
 */
@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {

  @Mock
  private UserService userService;

  private JwtUserDetailsService userDetailsService;

  @BeforeEach
  public void setup() {
    userDetailsService = new JwtUserDetailsService(userService);
  }

  @Test
  void testLoadUserByUsernameWhenUserExists() {
    User user = new User();
    user.setId(1L);
    user.setUsername("test@example.com");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("password");
    user.setStatus(Status.ACTIVE);
    user.setUpdated(null); // Set to null for simplicity

    Role role = new Role();
    role.setName("ROLE_USER");
    List<Role> roles = new ArrayList<>();
    roles.add(role);
    user.setRoles(roles);

    Mockito.when(userService.getByUsername("testUser")).thenReturn(user);

    UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

    assertNotNull(userDetails);
    assertEquals("testUser", userDetails.getUsername());
  }

  @Test
  void testLoadUserByUsernameWhenUserDoesNotExist() {
    Mockito.when(userService.getByUsername("nonExistentUser")).thenReturn(null);

    assertThrows(UsernameNotFoundException.class,
        () -> userDetailsService.loadUserByUsername("nonExistentUser"));
  }
}
