package com.example.securitysystem.services.impl;

import static com.example.securitysystem.entities.UserRole.USER;
import static java.time.LocalDateTime.now;

import com.example.securitysystem.dtos.RegistrationRequest;
import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.services.ConfirmationTokenService;
import com.example.securitysystem.services.RegistrationService;
import com.example.securitysystem.services.UserService;
import com.example.securitysystem.utils.EmailValidator;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user registration and confirmation.
 *
 * <p>This service handles the registration of new users and the confirmation
 * of their email addresses using tokens.</p>
 */
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

  private final UserService userService;
  private final EmailValidator emailValidator;
  private final ConfirmationTokenService confirmationTokenService;

  /**
   * Registers a new user based on the registration request.
   *
   * @param request The registration request containing user details.
   * @return A confirmation message.
   * @throws IllegalStateException if the email is not valid.
   */
  @Override
  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.email());

    if (!isValidEmail) {
      throw new IllegalStateException("Email is not valid!");
    }

    return userService.signUp(
      new User(request.firstName(), request.lastName(), request.email(), request.password(), USER)
    );
  }

  /**
   * Confirms a user's registration token.
   *
   * @param token The registration token to confirm.
   * @return A confirmation message.
   * @throws IllegalStateException if the token is not found, already confirmed, or expired.
   */
  @Override
  @Transactional
  public String confirmToken(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token)
        .orElseThrow(() -> new IllegalStateException("Token is not found!"));

    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("Email is already confirmed!");
    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(now())) {
      throw new IllegalStateException("Token is expired!");
    }

    confirmationTokenService.confirmToken(token);
    userService.enableUser(confirmationToken.getUser().getEmail());

    return "Confirmed!";
  }
}