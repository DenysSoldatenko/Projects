package com.example.securitysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.securitysystem.utils.EmailBuilder;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.services.impl.ConfirmationTokenServiceImpl;
import java.util.Optional;

import com.example.securitysystem.repositories.UserRepository;
import com.example.securitysystem.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the UserService class.
 */
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

  @Mock
  private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

  @Mock
  private EmailService emailService;

  @Mock
  private EmailBuilder emailBuilder;

  private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(
      emailService, emailBuilder,
      userRepository, passwordEncoder,
      confirmationTokenServiceImpl
    );
  }

  @Test
  void testLoadUserByUsername_UserExists() {
    User user = new User();
    user.setEmail("test@example.com");
    when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    UserDetails userDetails = userService.loadUserByUsername("test@example.com");

    assertNotNull(userDetails);
    assertEquals("test@example.com", userDetails.getUsername());
  }

  @Test
  void testLoadUserByUsername_UserNotFound() {
    when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class,
        () -> userService.loadUserByUsername("nonexistent@example.com"));
  }

  @Test
  void testSignUpUser_AlreadyExists() {
    User existingUser = new User();
    existingUser.setEmail("existing@example.com");
    when(userRepository.findByEmail("existing@example.com"))
        .thenReturn(Optional.of(existingUser));

    verify(userRepository, never()).save(any());
  }

  @Test
  void testSignUpUser_New() {
    User newUser = new User();
    newUser.setEmail("new@example.com");
    newUser.setPassword("password");
    when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(userRepository.save(newUser)).thenReturn(newUser);

    String token = userService.signUp(newUser);

    assertNotNull(token);
    assertEquals("encodedPassword", newUser.getPassword());
    verify(confirmationTokenServiceImpl, times(1)).saveToken(any(ConfirmationToken.class));
  }

  @Test
  void testEnableUser() {
    userService.enableUser("user@example.com");

    verify(userRepository, times(1)).enableAppUser("user@example.com");
  }
}
