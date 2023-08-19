package com.example.jwtsecuritysystem.configurations;

import com.example.jwtsecuritysystem.security.JwtUserDetailsService;
import com.example.jwtsecuritysystem.security.token.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private final JwtTokenFilter jwtTokenFilter;
  private final JwtUserDetailsService jwtUserDetailsService;

  private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
  private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

  @Autowired
  public SecurityConfig(JwtTokenFilter jwtTokenFilter,
                        JwtUserDetailsService jwtUserDetailsService) {
    this.jwtTokenFilter = jwtTokenFilter;
    this.jwtUserDetailsService = jwtUserDetailsService;
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
    return jwtUserDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .csrf(AbstractHttpConfigurer::disable)
    .authorizeHttpRequests(
      authorizeRequests ->
      authorizeRequests
        .requestMatchers(LOGIN_ENDPOINT).permitAll()
        .requestMatchers(ADMIN_ENDPOINT).hasRole("ROLE_ADMIN")
        .anyRequest().authenticated()
    )
    .authenticationProvider(authenticationProvider())
    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}