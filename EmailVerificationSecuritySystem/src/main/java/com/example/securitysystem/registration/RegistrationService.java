package com.example.securitysystem.registration;

import com.example.securitysystem.entities.User;
import com.example.securitysystem.entities.UserRole;
import com.example.securitysystem.appuser.AppUserService;
import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user registration and confirmation.
 */
@Service
@AllArgsConstructor
public class RegistrationService {

  private final AppUserService appUserService;
  private final EmailValidator emailValidator;
  private final ConfirmationTokenService confirmationTokenService;

  /**
   * Registers a new user based on the registration request.
   *
   * @param request The registration request containing user details.
   * @return A confirmation message.
   * @throws IllegalStateException if the email is not valid.
   */
  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.email());

    if (!isValidEmail) {
      throw new IllegalStateException("Email is not valid!");
    }

    return appUserService.signUpUser(
      new User(
          request.firstName(),
          request.lastName(),
          request.email(),
          request.password(),
          UserRole.USER
      )
    );
  }

  /**
   * Confirms a user's registration token.
   *
   * @param token The registration token to confirm.
   * @return A confirmation message.
   * @throws IllegalStateException if the token is not found, already confirmed, or expired.
   */
  @Transactional
  public String confirmToken(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService
        .getToken(token)
        .orElseThrow(() -> new IllegalStateException("Token is not found!"));

    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("Email is already confirmed!");
    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Token is expired!");
    }

    confirmationTokenService.setConfirmedAt(token);
    appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

    return "Confirmed!";
  }
}