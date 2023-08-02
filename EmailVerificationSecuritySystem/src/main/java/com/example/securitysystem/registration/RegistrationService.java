package com.example.securitysystem.registration;

import com.example.securitysystem.appuser.AppUser;
import com.example.securitysystem.appuser.AppUserRole;
import com.example.securitysystem.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

  private final AppUserService appUserService;
  private final EmailValidator emailValidator;

  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.email());

    if (!isValidEmail) {
      throw new IllegalStateException("email not valid");
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
}