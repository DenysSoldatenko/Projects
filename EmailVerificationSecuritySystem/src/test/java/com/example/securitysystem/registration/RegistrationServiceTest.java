package com.example.securitysystem.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.securitysystem.appuser.AppUser;
import com.example.securitysystem.appuser.AppUserService;
import com.example.securitysystem.registration.token.ConfirmationToken;
import com.example.securitysystem.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * Unit tests for the RegistrationService class.
 */
class RegistrationServiceTest {

  @Mock
  private AppUserService appUserService;

  @Mock
  private EmailValidator emailValidator;

  @Mock
  private ConfirmationTokenService confirmationTokenService;

  private RegistrationService registrationService;

  /**
   * Set up the necessary components for testing the RegistrationService.
   */
  @BeforeEach
  public void setUp() {
    appUserService = mock(AppUserService.class);
    emailValidator = mock(EmailValidator.class);
    confirmationTokenService = mock(ConfirmationTokenService.class);
    registrationService
        = new RegistrationService(appUserService, emailValidator, confirmationTokenService);
  }

  @Test
  void testRegisterValidEmail() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "john@example.com", "password123");
    when(emailValidator.test("john@example.com")).thenReturn(true);
    when(appUserService.signUpUser(any(AppUser.class))).thenReturn("Registration successful");

    String result = registrationService.register(request);

    assertEquals("Registration successful", result);
    verify(appUserService, times(1)).signUpUser(any(AppUser.class));
  }

  @Test
  void testRegisterInvalidEmail() {
    RegistrationRequest request
        = new RegistrationRequest("John", "Doe", "invalid-email", "password123");
    when(emailValidator.test("invalid-email")).thenReturn(false);

    assertThrows(IllegalStateException.class, () -> registrationService.register(request));
    verify(appUserService, never()).signUpUser(any(AppUser.class));
  }

  @Test
  void testConfirmTokenNotFound() {
    String token = "non-existent-token";
    when(confirmationTokenService.getToken(token)).thenReturn(Optional.empty());

    assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
    verify(confirmationTokenService, never()).setConfirmedAt(token);
    verify(appUserService, never()).enableAppUser(anyString());
  }

  @Test
  void testConfirmTokenAlreadyConfirmed() {
    String token = "token123";
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusHours(1),
        new AppUser()
    );
    confirmationToken.setConfirmedAt(LocalDateTime.now());

    when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

    assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
    verify(confirmationTokenService, never()).setConfirmedAt(token);
    verify(appUserService, never()).enableAppUser(anyString());
  }

  @Test
  void testConfirmTokenExpired() {
    String token = "token123";
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().minusMinutes(1),
        new AppUser()
    );

    when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));

    assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
    verify(confirmationTokenService, never()).setConfirmedAt(token);
    verify(appUserService, never()).enableAppUser(anyString());
  }
}
