package com.example.jwtauthsecuritysystem.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthenticationControllerTest {

  @Mock
  private AuthenticationService authenticationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegister() {
    RegisterRequest registerRequest = new RegisterRequest(
        "John",
        "Doe",
        "john@example.com",
        "password"
    );
    AuthenticationResponse authenticationResponse = new AuthenticationResponse("testToken");

    when(authenticationService.registerUser(registerRequest))
        .thenReturn(authenticationResponse);

    verify(authenticationService, times(1))
        .registerUser(registerRequest);
  }

  @Test
  void testAuthenticate() {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(
        "john@example.com",
        "password"
    );
    AuthenticationResponse authenticationResponse = new AuthenticationResponse("testToken");

    when(authenticationService.authenticate(authenticationRequest))
        .thenReturn(authenticationResponse);

    verify(authenticationService, times(1))
        .authenticate(authenticationRequest);
  }
}
