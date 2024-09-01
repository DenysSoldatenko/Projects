package com.example.securitysystem.services;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.securitysystem.dtos.RegistrationRequest;
import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.services.impl.ConfirmationTokenServiceImpl;
import com.example.securitysystem.services.impl.RegistrationServiceImpl;
import com.example.securitysystem.utils.EmailValidator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * Unit tests for the RegistrationService class.
 */
class RegistrationServiceImplTest {

  @Mock
  private UserService userService;

  @Mock
  private EmailValidator emailValidator;

  @Mock
  private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

  private RegistrationServiceImpl registrationService;

  /**
   * Set up the necessary components for testing the RegistrationService.
   */
  @BeforeEach
  public void setUp() {
    userService = mock(UserService.class);
    emailValidator = mock(EmailValidator.class);
    confirmationTokenServiceImpl = mock(ConfirmationTokenServiceImpl.class);
    registrationService
        = new RegistrationServiceImpl(userService, emailValidator, confirmationTokenServiceImpl);
  }

  @Test
  void testRegisterValidEmail() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");
    when(emailValidator.test("john@example.com")).thenReturn(true);
    when(userService.signUp(any(User.class))).thenReturn("Registration successful");

    String result = registrationService.register(request);

    assertEquals("Registration successful", result);
    verify(userService, times(1)).signUp(any(User.class));
  }

  @Test
  void testRegisterInvalidEmail() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "invalid-email", "password123");
    when(emailValidator.test("invalid-email")).thenReturn(false);

    assertThrows(IllegalStateException.class, () -> registrationService.register(request));
    verify(userService, never()).signUp(any(User.class));
  }

  @Test
  void testConfirmTokenNotFound() {
    String token = "non-existent-token";
    when(confirmationTokenServiceImpl.findByToken(token)).thenReturn(Optional.empty());

    assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
    verify(confirmationTokenServiceImpl, never()).confirmToken(token);
    verify(userService, never()).enableUser(anyString());
  }

  @Test
  void testConfirmTokenAlreadyConfirmed() {
    String token = "token123";
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token, now(), now().plusHours(1), new User()
    );
    confirmationToken.setConfirmedAt(now());

    when(confirmationTokenServiceImpl.findByToken(token)).thenReturn(Optional.of(confirmationToken));

    assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
    verify(confirmationTokenServiceImpl, never()).confirmToken(token);
    verify(userService, never()).enableUser(anyString());
  }
}
