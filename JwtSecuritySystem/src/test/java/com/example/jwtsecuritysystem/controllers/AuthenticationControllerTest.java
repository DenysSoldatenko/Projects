package com.example.jwtsecuritysystem.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.security.token.JwtTokenProvider;
import com.example.jwtsecuritysystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit tests for the AuthenticationController class.
 */
@ExtendWith(SpringExtension.class)
public class AuthenticationControllerTest {

  private AuthenticationController authenticationController;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private UserService userService;

  @BeforeEach
  public void setUp() {
    authenticationController
        = new AuthenticationController(authenticationManager, jwtTokenProvider, userService);
  }

  @Test
  public void testLoginWithInvalidUser() {
    AuthenticationRequestDto requestDto = new AuthenticationRequestDto("invalidUser", "password");
    when(userService.findByUsername("invalidUser")).thenReturn(null);

    assertThrows(UsernameNotFoundException.class, () -> authenticationController.login(requestDto));
  }
}
