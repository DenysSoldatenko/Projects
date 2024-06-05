package com.example.securitysystem.services.impl;

import com.example.securitysystem.email.EmailBuilder;
import com.example.securitysystem.entities.User;
import com.example.securitysystem.entities.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.example.securitysystem.repositories.UserRepository;
import com.example.securitysystem.services.EmailService;
import com.example.securitysystem.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

  private static final String USER_NOT_FOUND_MSG = "User with email %s not found";

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final ConfirmationTokenServiceImpl confirmationTokenServiceImpl;
  private final EmailService emailService;
  private final EmailBuilder emailBuilder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
  }

  /**
   * Registers a new user.
   *
   * @param appUser The user to be registered.
   * @return The confirmation token generated for the user.
   */
  @Override
  public String signUp(User appUser) {
    Optional<User> existingUser = userRepository.findByEmail(appUser.getEmail());

    if (existingUser.isPresent()) {
      return handleExistingUser(existingUser.get());
    } else {
      String encodedPassword = passwordEncoder.encode(appUser.getPassword());
      appUser.setPassword(encodedPassword);
      userRepository.save(appUser);

      return saveToken(appUser);
    }
  }

  private String handleExistingUser(User existingUser) {
    Optional<ConfirmationToken> confirmationToken = confirmationTokenServiceImpl.findValidToken(existingUser);

    if (confirmationToken.isPresent()) {
      throw new IllegalStateException("User is already confirmed!");
    } else {
      return saveToken(existingUser);
    }
  }

  @Override
  public String saveToken(User user) {
    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(
        token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user
    );

    confirmationTokenServiceImpl.saveToken(confirmationToken);

    String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
    emailService.sendEmail(user.getEmail(), emailBuilder.build(user.getFirstName(), link));
    return token;
  }

  @Override
  public void enableUser(String email) {
    userRepository.enableAppUser(email);
  }
}