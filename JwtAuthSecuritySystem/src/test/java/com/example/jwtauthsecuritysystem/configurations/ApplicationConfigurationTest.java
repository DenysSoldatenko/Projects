package com.example.jwtauthsecuritysystem.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.jwtauthsecuritysystem.repositories.UserRepository;
import com.example.jwtauthsecuritysystem.security.ApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

class ApplicationConfigurationTest {

  @Test
  void testUserDetailsServiceBean() {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    ApplicationConfiguration applicationConfiguration
        = new ApplicationConfiguration(userRepository);
    UserDetailsService userDetailsService = applicationConfiguration.userDetailsService();

    assertNotNull(userDetailsService);
  }

  @Test
  void testAuthenticationProviderBean() {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    ApplicationConfiguration applicationConfiguration
        = new ApplicationConfiguration(userRepository);
    AuthenticationProvider authenticationProvider
        = applicationConfiguration.authenticationProvider();

    assertNotNull(authenticationProvider);
  }

  @Test
  void testPasswordEncoderBean() {
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    ApplicationConfiguration applicationConfiguration
        = new ApplicationConfiguration(userRepository);
    PasswordEncoder passwordEncoder = applicationConfiguration.passwordEncoder();

    assertNotNull(passwordEncoder);
  }
}
