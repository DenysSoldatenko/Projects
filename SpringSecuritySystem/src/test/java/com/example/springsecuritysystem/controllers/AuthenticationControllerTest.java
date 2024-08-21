package com.example.springsecuritysystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.springsecuritysystem.security.JwtService;
import com.example.springsecuritysystem.dao.UserDao;
import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Unit tests for the AuthenticationController class.
 */
@SpringBootTest
class AuthenticationControllerTest {

  private AuthenticationController authenticationController;
  private AuthenticationManager authenticationManager;
  private UserDao userDao;
  private JwtService jwtService;

  /**
   * Set up the necessary components for testing the AuthenticationController.
   */
  @BeforeEach
  public void setUp() {
    authenticationManager = mock(AuthenticationManager.class);
    userDao = mock(UserDao.class);
    jwtService = mock(JwtService.class);
    authenticationController
      = new AuthenticationController(authenticationManager, userDao, jwtService);
  }

  @Test
  void testAuthenticateWithValidCredentials() {
    AuthenticationRequest request = new AuthenticationRequest("john.doe@gmail.com", "password");
    UserDetails userDetails = new User(
        "john.doe@gmail.com",
        "password",
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    );
    String token = "mocked-token";

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(null);
    when(userDao.findUserByEmail("john.doe@gmail.com")).thenReturn(userDetails);
    when(jwtService.generateToken(userDetails)).thenReturn(token);

    ResponseEntity<String> response = authenticationController.authenticate(request);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(token, response.getBody());
  }

  @Test
  void testAuthenticateWithInvalidCredentials() {
    AuthenticationRequest request
        = new AuthenticationRequest("john.doe@gmail.com", "invalid-password");

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenThrow(BadCredentialsException.class); // Simulating bad credentials exception

    ResponseEntity<String> response = authenticationController.authenticate(request);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertNull(response.getBody());
  }

  @Test
  void testAuthenticateWithUnknownUser() {
    AuthenticationRequest request = new AuthenticationRequest("unknown@gmail.com", "password");

    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(null);
    when(userDao.findUserByEmail("unknown@gmail.com")).thenReturn(null);

    ResponseEntity<String> response = authenticationController.authenticate(request);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Some error has occurred!", response.getBody());
  }
}
