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
public class SecurityConfiguration {

  private final JwtTokenFilter jwtTokenFilter;

  private static final String ADMIN_ENDPOINT = "/api/v*/admin/**";
  private static final String[] PUBLIC_ROUTES = {
    "/api/v*/auth/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Constructor for the {@link SecurityConfiguration} class.
   *
   * <p>Initializes the security configuration with the provided {@link JwtTokenFilter} instance.
   * This filter will be used to intercept and
   * validate JWT tokens during the authentication process.</p>
   *
   * @param jwtTokenFilter The {@link JwtTokenFilter} to be used for JWT validation.
   */
  @Autowired
  public SecurityConfiguration(JwtTokenFilter jwtTokenFilter) {
    this.jwtTokenFilter = jwtTokenFilter;
  }

  /**
   * Creates and configures an {@link AuthenticationManager} bean.
   *
   * <p>This method retrieves the {@link AuthenticationManager} from the provided
   * {@link AuthenticationConfiguration} instance, enabling Spring Security to manage
   * the authentication process for users.</p>
   *
   * @param config The {@link AuthenticationConfiguration} to get the {@link AuthenticationManager}.
   * @return The configured {@link AuthenticationManager}.
   * @throws Exception If there is an issue getting the {@link AuthenticationManager}.
   */
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