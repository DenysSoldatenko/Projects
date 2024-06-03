package com.example.securitysystem.registration.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ConfirmationToken class.
 */
class ConfirmationTokenTest {

  @Test
  void testConstructorAndGetterMethods() {
    User appUser = new User();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);

    assertEquals("token123", confirmationToken.getToken());
    assertEquals(createdAt, confirmationToken.getCreatedAt());
    assertEquals(expiresAt, confirmationToken.getExpiresAt());
    assertNull(confirmationToken.getConfirmedAt());
    assertEquals(appUser, confirmationToken.getUser());
  }

  @Test
  void testNoArgsConstructor() {
    ConfirmationToken confirmationToken = new ConfirmationToken();

    assertNull(confirmationToken.getToken());
    assertNull(confirmationToken.getCreatedAt());
    assertNull(confirmationToken.getExpiresAt());
    assertNull(confirmationToken.getConfirmedAt());
    assertNull(confirmationToken.getUser());
  }

  @Test
  void testId() {
    User appUser = new User();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);
    confirmationToken.setId(1L);

    assertEquals(1L, confirmationToken.getId());
  }

  @Test
  void testConfirmedAt() {
    User appUser = new User();
    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);
    LocalDateTime confirmedAt = LocalDateTime.now();

    ConfirmationToken confirmationToken
        = new ConfirmationToken("token123", createdAt, expiresAt, appUser);
    confirmationToken.setConfirmedAt(confirmedAt);

    assertEquals(confirmedAt, confirmationToken.getConfirmedAt());
  }
}
