package com.example.jwtauthsecuritysystem.security;

import com.example.jwtauthsecuritysystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for Spring Security and authentication-related beans.
 *
 * <p>This class configures beans for user authentication,
 * password encoding, and user details retrieval.
 * It includes beans for {@link UserDetailsService}, {@link AuthenticationProvider},
 * {@link AuthenticationManager}, and {@link PasswordEncoder}.</p>
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

  private final UserRepository userRepository;

  /**
   * Provides a {@link UserDetailsService} implementation to load user details by email.
   *
   * @return A {@link UserDetailsService} that retrieves user details from the database.
   * @throws UsernameNotFoundException if no user is found with the given username (email).
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }

  /**
   * Provides an {@link AuthenticationProvider} bean using {@link DaoAuthenticationProvider}.
   *
   * @return An {@link AuthenticationProvider} that authenticates users
   *     via a database-backed {@link UserDetailsService}.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  /**
   * Provides an {@link AuthenticationManager} bean to manage authentication requests.
   *
   * @param config The {@link AuthenticationConfiguration} instance for configuring authentication.
   * @return An instance of {@link AuthenticationManager} for managing authentication.
   * @throws Exception if an error occurs while getting the {@link AuthenticationManager}.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Provides a {@link PasswordEncoder} bean for encrypting user passwords.
   *
   * @return A {@link PasswordEncoder} instance using {@link BCryptPasswordEncoder}.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
