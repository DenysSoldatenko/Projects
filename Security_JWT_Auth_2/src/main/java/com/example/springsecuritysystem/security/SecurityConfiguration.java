package com.example.springsecuritysystem.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.example.springsecuritysystem.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

  private final UserDao userDao;
  private final JwtAuthFilter jwtAuthFilter;

  private static final String[] PUBLIC_ROUTES = {
    "/api/v*/auth/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Configures the security filter chain.
   *
   * @param http The HttpSecurity object to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If configuration fails.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .csrf(AbstractHttpConfigurer::disable)
    .authorizeHttpRequests(
      authorizeRequests ->
        authorizeRequests
        .requestMatchers(PUBLIC_ROUTES).permitAll()
        .anyRequest().authenticated()
    )
    .authenticationProvider(authenticationProvider())
    .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Configures the authentication provider.
   *
   * @return The configured AuthenticationProvider.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return userDao::findUserByEmail;
  }
}
