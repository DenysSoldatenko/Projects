package com.example.springsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springsecuritysystem.dao.UserDao;
import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import com.example.springsecuritysystem.security.JwtService;
import com.example.springsecuritysystem.services.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Unit tests for the AuthenticationServiceImpl class.
 */
class AuthenticationServiceImplTest {

  @Mock
  private UserDao userDao;

  @Mock
  private JwtService jwtService;

  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private AuthenticationServiceImpl authenticationService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldAuthenticateUserAndGenerateToken() {
    String email = "john.doe@gmail.com";
    String password = "password";
    String token = "mock-jwt-token";

    AuthenticationRequest request = new AuthenticationRequest(email, password);

    UserDetails mockUserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities("ROLE_USER")
        .build();

    when(userDao.findUserByEmail(email)).thenReturn(mockUserDetails);
    when(jwtService.generateToken(mockUserDetails)).thenReturn(token);

    String resultToken = authenticationService.authenticateAndGenerateToken(request);

    assertEquals(token, resultToken);
    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
  }

  @Test
  void shouldThrowBadCredentialsExceptionWhenAuthenticationFails() {
    String email = "john.doe@gmail.com";
    String password = "wrongpassword";
    AuthenticationRequest request = new AuthenticationRequest(email, password);

    UserDetails mockUserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities("ROLE_USER")
        .build();

    when(userDao.findUserByEmail(email)).thenReturn(mockUserDetails);

    doThrow(new BadCredentialsException("Invalid credentials"))
      .when(authenticationManager)
        .authenticate(any(UsernamePasswordAuthenticationToken.class));

    assertThrows(BadCredentialsException.class, () -> authenticationService.authenticateAndGenerateToken(request));

    verify(jwtService, never()).generateToken(any());
  }
}
