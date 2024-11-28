package com.example.securitysystem.configurations;

import com.example.securitysystem.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for setting up web security.
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

  private final UserServiceImpl userService;
  private final BCryptPasswordEncoder passwordEncoder;

  private static final String[] PUBLIC_ROUTES = {
    "/api/v*/registration/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Creates and configures an {@link AuthenticationManager} bean.
   *
   * <p>This bean is used by Spring Security to authenticate users.</p>
   *
   * @param configuration The {@link AuthenticationConfiguration} to get the {@link AuthenticationManager}.
   * @return The {@link AuthenticationManager} bean.
   * @throws Exception If an error occurs while retrieving the {@link AuthenticationManager}.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }


  /**
   * Creates a DaoAuthenticationProvider bean for password authentication.
   *
   * @return The configured DaoAuthenticationProvider.
   */
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userService);
    return provider;
  }

  /**
   * Configures the default security filter chain.
   *
   * @param http The HttpSecurity object to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception if an error occurs while configuring the security filter chain.
   */
  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
          authorizeRequests ->
            authorizeRequests
              .requestMatchers(PUBLIC_ROUTES).permitAll()
              .anyRequest().authenticated()
        )
        .formLogin(
          formLogin ->
            formLogin
              .defaultSuccessUrl("/api/v1/registration/index", true)
        );

    return http.build();
  }
}