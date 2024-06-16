package com.example.jwtauthsecuritysystem.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtServiceIntegrationTest {

  @Autowired
  private JwtService jwtService;

  @Test
  void testGenerateAndValidateToken() {
    UserDetails userDetails = User.withUsername("testuser")
        .password("password")
        .authorities("ROLE_USER")
        .build();

    String token = jwtService.generateToken(userDetails);

    assertTrue(jwtService.isTokenValid(token, userDetails));
  }

  @Test
  void testGenerateAndExtractClaim() {
    UserDetails userDetails = User.withUsername("testuser")
        .password("password")
        .authorities("ROLE_USER")
        .build();

    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("customClaim", "someValue");
    String token = jwtService.generateToken(extraClaims, userDetails);

    String customClaimValue = jwtService
        .extractClaim(token, claims -> claims.get("customClaim", String.class));

    assertEquals("someValue", customClaimValue);
  }
}
