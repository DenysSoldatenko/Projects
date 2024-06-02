package com.example.securitysystem.appuser;

import com.example.securitysystem.email.EmailBuilder;
import com.example.securitysystem.email.EmailSender;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.entities.ConfirmationToken;
import com.example.securitysystem.registration.token.ConfirmationTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing application users and user-related operations.
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

  private static final String USER_NOT_FOUND_MSG = "User with email %s not found";

  private final AppUserRepository appUserRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ConfirmationTokenService confirmationTokenService;
  private final EmailSender emailSender;
  private final EmailBuilder emailBuilder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email)
    .orElseThrow(
      () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
    );
  }

  /**
   * Registers a new user.
   *
   * @param appUser The user to be registered.
   * @return The confirmation token generated for the user.
   */
  public String signUpUser(User appUser) {
    Optional<User> existingUser = appUserRepository.findByEmail(appUser.getEmail());

    if (existingUser.isPresent()) {
      return handleExistingUser(existingUser.get());
    } else {
      String encodedPassword = passwordEncoder.encode(appUser.getPassword());
      appUser.setPassword(encodedPassword);
      appUserRepository.save(appUser);

      return createAndSaveToken(appUser);
    }
  }

  private String handleExistingUser(User existingUser) {
    Optional<ConfirmationToken> confirmationToken = confirmationTokenService
        .findNonExpiredToken(existingUser);

    if (confirmationToken.isPresent()) {
      throw new IllegalStateException("User is already confirmed!");
    } else {
      return createAndSaveToken(existingUser);
    }
  }

  private String createAndSaveToken(User appUser) {
    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(15),
        appUser
    );

    confirmationTokenService.saveConfirmationToken(confirmationToken);

    String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
    emailSender.send(appUser.getEmail(), emailBuilder.build(appUser.getFirstName(), link));
    return token;
  }

  public void enableAppUser(String email) {
    appUserRepository.enableAppUser(email);
  }
}