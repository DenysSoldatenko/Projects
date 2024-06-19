package com.example.jwtsecuritysystem.configurations;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.example.jwtsecuritysystem.security.token.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for setting up security in the Spring Security System.
 */
@Configuration
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;

  private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
  private static final String[] PUBLIC_ROUTES = {
    "/api/v1/auth/login",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  @Autowired
  public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
    this.jwtTokenFilter = jwtTokenFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  /**
   * Configures the SecurityFilterChain for HTTP security.
   *
   * @param http The HttpSecurity object to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(
        authorizeRequests ->
          authorizeRequests
            .requestMatchers(PUBLIC_ROUTES).permitAll()
            .requestMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
            .anyRequest().authenticated()
      )
      .sessionManagement(
        sessionManagementConfigurer ->
          sessionManagementConfigurer
            .sessionCreationPolicy(STATELESS)
      )
      .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}