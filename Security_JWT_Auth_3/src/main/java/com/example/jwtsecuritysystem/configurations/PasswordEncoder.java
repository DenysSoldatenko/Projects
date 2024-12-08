package com.example.jwtsecuritysystem.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for setting up a BCrypt password encoder bean.
 */
@Configuration
public class PasswordEncoder {

  /**
   * Creates a {@link BCryptPasswordEncoder} bean for password encoding.
   *
   * <p>This method provides a {@link BCryptPasswordEncoder} instance that can be used
   * throughout the application for securely encoding and validating passwords using the
   * BCrypt hashing algorithm.</p>
   *
   * @return A {@link BCryptPasswordEncoder} instance for encoding and validating passwords.
   */
  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}