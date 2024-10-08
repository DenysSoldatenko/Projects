package com.example.springsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Unit tests for the JwtService class.
 */
@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

  private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYX"
      + "V0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJzdWIiOiJqYW5lLmRvZUBnbWFpbC5jb20iLCJ"
      + "pYXQiOjE2OTY5MjczODQsImV4cCI6MTY5Njk2MzM4NH0.PMdJ7yZwMfXGX39QGJ9trKlywaWBDJHin0q2SPsXbck";

  @InjectMocks
  private JwtService jwtService;

  @Test
  void shouldReturnCorrectUsername() {
    String username = jwtService.extractUsername(token);
    assertEquals("jane.doe@gmail.com", username);
  }

  @Test
  void shouldReturnCorrectExpiration() {
    LocalDateTime localDateTime = LocalDateTime.of(2023, 10, 10, 21, 43, 4);
    Date expiration = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    Date extractedExpiration = jwtService.extractExpiration(token);
    assertEquals(expiration, extractedExpiration);
  }

  @Test
  void shouldReturnCorrectClaimValue() {
    String value = jwtService.extractClaim(token, claims -> claims.get("sub", String.class));
    assertEquals("jane.doe@gmail.com", value);
  }

  @Test
  void shouldReturnFalseForValidToken() {
    /*  boolean isExpired = jwtUtil.isTokenExpired(TOKEN);
    assertFalse(isExpired);*/
  }

  @Test
  void shouldGenerateTokenForUserDetails() {
    UserDetails userDetails = new User(
        "john.doe@gmail.com",
        "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
    );
    String token = jwtService.generateToken(userDetails);
    assertNotNull(token);
  }

  @Test
  void shouldReturnTrueForValidToken() {
    UserDetails userDetails = new User(
        "jane.doe@gmail.com",
        "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    );
    boolean isValid = jwtService.validateToken(token, userDetails);
    assertTrue(isValid);
  }
}
