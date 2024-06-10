package com.example.securitysystem.services;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.repositories.ConfirmationTokenRepository;
import com.example.securitysystem.services.impl.ConfirmationTokenServiceImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the ConfirmationTokenServiceImpl class.
 */
@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceImplTest {

  @Mock
  private ConfirmationTokenRepository confirmationTokenRepository;
  private ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

  @BeforeEach
  public void setUp() {
    confirmationTokenServiceImpl = new ConfirmationTokenServiceImpl(confirmationTokenRepository);
  }

  @Test
  void testSaveToken() {
    ConfirmationToken token = new ConfirmationToken(
        "token123", now(), now().plusHours(1), new User()
    );
    confirmationTokenServiceImpl.saveToken(token);
    verify(confirmationTokenRepository).save(token);
  }

  @Test
  void testFindByToken() {
    String tokenValue = "token123";
    ConfirmationToken token = new ConfirmationToken(
        tokenValue, now(), now().plusHours(1), new User()
    );
    when(confirmationTokenRepository.findByToken(tokenValue))
        .thenReturn(Optional.of(token));

    Optional<ConfirmationToken> retrievedToken
        = confirmationTokenServiceImpl.findByToken(tokenValue);

    assertTrue(retrievedToken.isPresent());
    assertEquals(tokenValue, retrievedToken.get().getToken());
  }

  @Test
  void testFindValidToken() {
    User appUser = new User();
    LocalDateTime createdAt = now().minusMinutes(30);
    LocalDateTime expiresAt = now().plusHours(1);
    ConfirmationToken token = new ConfirmationToken("token123", createdAt, expiresAt, appUser);

    when(confirmationTokenRepository.findFirstByUserAndConfirmedAtIsNotNull(appUser))
        .thenReturn(Optional.of(token));

    Optional<ConfirmationToken> nonExpiredToken
        = confirmationTokenServiceImpl.findValidToken(appUser);

    assertTrue(nonExpiredToken.isPresent());
    assertEquals("token123", nonExpiredToken.get().getToken());
  }

  @Test
  void testConfirmToken() {
    String tokenValue = "token123";
    ConfirmationToken token = new ConfirmationToken(
        tokenValue, now(), now().plusHours(1), new User()
    );
    confirmationTokenServiceImpl.confirmToken(tokenValue);
    assertNull(token.getConfirmedAt());
  }
}
