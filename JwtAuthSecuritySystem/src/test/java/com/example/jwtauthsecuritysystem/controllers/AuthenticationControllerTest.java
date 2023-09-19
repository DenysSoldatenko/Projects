package com.example.jwtauthsecuritysystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AuthenticationControllerTest {

  @Mock
  private AuthenticationService authenticationService;

  @InjectMocks
  private AuthenticationController authenticationController;

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

    ResponseEntity<AuthenticationResponse> responseEntity = authenticationController
        .register(registerRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(authenticationResponse, responseEntity.getBody());

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

    ResponseEntity<AuthenticationResponse> responseEntity = authenticationController
        .authenticate(authenticationRequest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(authenticationResponse, responseEntity.getBody());

    verify(authenticationService, times(1))
        .authenticate(authenticationRequest);
  }
}
