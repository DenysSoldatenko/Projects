package com.example.securitysystem.appuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.securitysystem.email.EmailBuilder;
import com.example.securitysystem.email.EmailSender;
import com.example.securitysystem.registration.token.ConfirmationToken;
import com.example.securitysystem.registration.token.ConfirmationTokenService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the AppUserService class.
 */
@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {

  @Mock
  private AppUserRepository appUserRepository;

  @Mock
  private BCryptPasswordEncoder passwordEncoder;

  @Mock
  private ConfirmationTokenService confirmationTokenService;

  @Mock
  private EmailSender emailSender;

  @Mock
  private EmailBuilder emailBuilder;

  private AppUserService appUserService;

  @BeforeEach
  public void setUp() {
    appUserService = new AppUserService(appUserRepository, passwordEncoder,
      confirmationTokenService, emailSender, emailBuilder);
  }

  @Test
  public void testLoadUserByUsername_UserExists() {
    AppUser user = new AppUser();
    user.setEmail("test@example.com");
    when(appUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    UserDetails userDetails = appUserService.loadUserByUsername("test@example.com");

    assertNotNull(userDetails);
    assertEquals("test@example.com", userDetails.getUsername());
  }

  @Test
  public void testLoadUserByUsername_UserNotFound() {
    when(appUserRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class,
        () -> appUserService.loadUserByUsername("nonexistent@example.com"));
  }

  @Test
  public void testSignUpUser_UserAlreadyExists() {
    AppUser existingUser = new AppUser();
    existingUser.setEmail("existing@example.com");
    when(appUserRepository.findByEmail("existing@example.com"))
        .thenReturn(Optional.of(existingUser));

    verify(appUserRepository, never()).save(any());
  }

  @Test
  public void testSignUpUser_NewUser() {
    AppUser newUser = new AppUser();
    newUser.setEmail("new@example.com");
    newUser.setPassword("password");
    when(appUserRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(appUserRepository.save(newUser)).thenReturn(newUser);

    String token = appUserService.signUpUser(newUser);

    assertNotNull(token);
    assertEquals("encodedPassword", newUser.getPassword());
    verify(confirmationTokenService, times(1)).saveConfirmationToken(any(ConfirmationToken.class));
  }

  @Test
  public void testEnableAppUser() {
    appUserService.enableAppUser("user@example.com");

    verify(appUserRepository, times(1)).enableAppUser("user@example.com");
  }
}
