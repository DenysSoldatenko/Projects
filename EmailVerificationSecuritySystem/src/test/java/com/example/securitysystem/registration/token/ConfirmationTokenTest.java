package com.example.securitysystem.registration.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.securitysystem.appuser.AppUser;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ConfirmationToken class.
 */
class ConfirmationTokenTest {

  @Test
  void testConstructorAndGetterMethods() {
    AppUser appUser = new AppUser();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);

    assertEquals("token123", confirmationToken.getToken());
    assertEquals(createdAt, confirmationToken.getCreatedAt());
    assertEquals(expiresAt, confirmationToken.getExpiresAt());
    assertNull(confirmationToken.getConfirmedAt());
    assertEquals(appUser, confirmationToken.getAppUser());
  }

  @Test
  void testNoArgsConstructor() {
    ConfirmationToken confirmationToken = new ConfirmationToken();

    assertNull(confirmationToken.getToken());
    assertNull(confirmationToken.getCreatedAt());
    assertNull(confirmationToken.getExpiresAt());
    assertNull(confirmationToken.getConfirmedAt());
    assertNull(confirmationToken.getAppUser());
  }

  @Test
  void testId() {
    AppUser appUser = new AppUser();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);
    confirmationToken.setId(1L);

    assertEquals(1L, confirmationToken.getId());
  }

  @Test
  void testConfirmedAt() {
    AppUser appUser = new AppUser();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);
    LocalDateTime confirmedAt = LocalDateTime.now();

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);
    confirmationToken.setConfirmedAt(confirmedAt);

    assertEquals(confirmedAt, confirmationToken.getConfirmedAt());
  }
}
