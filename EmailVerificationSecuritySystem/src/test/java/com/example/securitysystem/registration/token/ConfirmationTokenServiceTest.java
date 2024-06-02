package com.example.securitysystem.registration.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the ConfirmationTokenService class.
 */
@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {

  @Mock
  private ConfirmationTokenRepository confirmationTokenRepository;
  private ConfirmationTokenService confirmationTokenService;

  @BeforeEach
  public void setUp() {
    confirmationTokenService = new ConfirmationTokenService(confirmationTokenRepository);
  }

  @Test
  void testSaveConfirmationToken() {
    ConfirmationToken token = new ConfirmationToken(
        "token123",
        LocalDateTime.now(),
        LocalDateTime.now().plusHours(1),
        new User()
    );
    confirmationTokenService.saveConfirmationToken(token);

    Mockito.verify(confirmationTokenRepository).save(token);
  }

  @Test
  void testGetToken() {
    String tokenValue = "token123";
    ConfirmationToken token = new ConfirmationToken(
        tokenValue,
        LocalDateTime.now(),
        LocalDateTime.now().plusHours(1),
        new User()
    );
    Mockito.when(confirmationTokenRepository.findByToken(tokenValue))
        .thenReturn(Optional.of(token));

    Optional<ConfirmationToken> retrievedToken = confirmationTokenService.getToken(tokenValue);

    assertTrue(retrievedToken.isPresent());
    assertEquals(tokenValue, retrievedToken.get().getToken());
  }

  @Test
  void testFindNonExpiredToken() {
    User appUser = new User();
    LocalDateTime createdAt = LocalDateTime.now().minusMinutes(30);
    LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);
    ConfirmationToken token = new ConfirmationToken("token123", createdAt, expiresAt, appUser);

    Mockito.when(confirmationTokenRepository.findFirstByAppUserAndConfirmedAtIsNotNull(appUser))
        .thenReturn(Optional.of(token));

    Optional<ConfirmationToken> nonExpiredToken
        = confirmationTokenService.findNonExpiredToken(appUser);

    assertTrue(nonExpiredToken.isPresent());
    assertEquals("token123", nonExpiredToken.get().getToken());
  }

  @Test
  void testSetConfirmedAt() {
    String tokenValue = "token123";
    ConfirmationToken token = new ConfirmationToken(tokenValue, LocalDateTime.now(),
        LocalDateTime.now().plusHours(1), new User());

    confirmationTokenService.setConfirmedAt(tokenValue);

    assertNull(token.getConfirmedAt());
  }
}
