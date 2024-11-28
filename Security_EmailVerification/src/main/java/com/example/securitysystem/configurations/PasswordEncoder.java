package com.example.securitysystem.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for creating a {@link BCryptPasswordEncoder} bean.
 *
 * <p>This class defines a bean for the {@link BCryptPasswordEncoder}, which is used
 * for encrypting passwords using the BCrypt hashing algorithm.</p>
 */
@Configuration
public class PasswordEncoder {

  /**
   * Creates a {@link BCryptPasswordEncoder} bean for password encoding.
   *
   * @return A new instance of {@link BCryptPasswordEncoder}.
   */
  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
