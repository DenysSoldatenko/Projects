package com.example.securitysystem.registration;

import com.example.securitysystem.appuser.AppUser;
import com.example.securitysystem.appuser.AppUserRole;
import com.example.securitysystem.appuser.AppUserService;
import com.example.securitysystem.registration.token.ConfirmationToken;
import com.example.securitysystem.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final AppUserService appUserService;
  private final EmailValidator emailValidator;
  private final ConfirmationTokenService confirmationTokenService;

  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.email());

    if (!isValidEmail) {
      throw new IllegalStateException("Email is not valid!");
    }

    return appUserService.signUpUser(
      new AppUser(
          request.firstName(),
          request.lastName(),
          request.email(),
          request.password(),
          AppUserRole.USER
      )
    );
  }

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