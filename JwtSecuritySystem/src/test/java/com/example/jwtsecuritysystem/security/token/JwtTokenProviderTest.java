package com.example.jwtsecuritysystem.security.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.models.Role;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Unit tests for the JwtTokenProvider class.
 */
public class JwtTokenProviderTest {

  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private UserDetailsService userDetailsService;

  /**
   * Set up the JwtTokenProvider for testing with custom configurations.
   */
  @BeforeEach
  public void setup() {
    jwtTokenProvider = new JwtTokenProvider(userDetailsService);
    jwtTokenProvider.setSecret("mySecretKey");
    jwtTokenProvider.setValidityInMilliseconds(3600000); // Set the validity to 1 hour for testing
  }

  @Test
  public void testCreateToken() {
    String username = "testUser";
    Role role = new Role();
    role.setName("ROLE_USER");
    List<Role> roles = Collections.singletonList(role);
    String token = jwtTokenProvider.createToken(username, roles);

    assertNotNull(token);
  }

  @Test
  public void testResolveTokenValidBearerToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getHeader("Authorization")).thenReturn("Bearer_validToken");

    String token = jwtTokenProvider.resolveToken(request);

    assertEquals("validToken", token);
  }

  @Test
  public void testResolveTokenInvalidBearerToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getHeader("Authorization")).thenReturn("InvalidToken");

    String token = jwtTokenProvider.resolveToken(request);

    assertNull(token);
  }

  @Test
  public void testResolveTokenNoBearerToken() {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getHeader("Authorization")).thenReturn(null);

    String token = jwtTokenProvider.resolveToken(request);

    assertNull(token);
  }

  @Test
  public void testValidateTokenWithExpiredToken() {
    jwtTokenProvider.setValidityInMilliseconds(-1);
    String username = "testUser";
    String token = jwtTokenProvider.createToken(username, Collections.emptyList());

    assertThrows(ExpiredJwtException.class, () -> jwtTokenProvider.validateToken(token));
  }

  @Test
  public void testValidateTokenWithValidToken() {
    String username = "testUser";
    String token = jwtTokenProvider.createToken(username, Collections.emptyList());

    boolean isValid = jwtTokenProvider.validateToken(token);

    assertTrue(isValid);
  }

  @Test
  public void testGetUsername() {
    String username = "testUser";
    String token = jwtTokenProvider.createToken(username, Collections.emptyList());

    String extractedUsername = jwtTokenProvider.getUsername(token);

    assertEquals(username, extractedUsername);
  }


  @Test
  public void testValidateValidToken() {
    String username = "testUser";
    String token = jwtTokenProvider.createToken(username, Collections.emptyList());

    boolean isValid = jwtTokenProvider.validateToken(token);

    assertTrue(isValid);
  }

  @Test
  public void testValidateExpiredToken() {
    jwtTokenProvider.setValidityInMilliseconds(-1);
    String username = "testUser";
    String token = jwtTokenProvider.createToken(username, Collections.emptyList());

    assertThrows(ExpiredJwtException.class, () -> jwtTokenProvider.validateToken(token));
  }
}
