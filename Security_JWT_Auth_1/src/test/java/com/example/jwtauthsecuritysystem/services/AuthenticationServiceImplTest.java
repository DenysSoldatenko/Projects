package com.example.jwtauthsecuritysystem.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.User;
import com.example.jwtauthsecuritysystem.repositories.UserRepository;
import com.example.jwtauthsecuritysystem.security.JwtService;
import com.example.jwtauthsecuritysystem.services.impl.AuthenticationServiceImpl;
import com.example.jwtauthsecuritysystem.utils.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AuthenticationServiceImplTest {

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private UserRepository userRepository;

  @Mock
  private JwtService jwtService;

  @Mock
  private UserFactory userFactory;

  @InjectMocks
  private AuthenticationServiceImpl authenticationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegisterUser() {
    RegisterRequest registerRequest = new RegisterRequest(
        "John", "Doe",
        "john@example.com", "password"
    );
    User mockedUser = mock(User.class);
    when(userFactory.createUserFromRequest(registerRequest)).thenReturn(mockedUser);

    AuthenticationResponse response = authenticationService.registerUser(registerRequest);

    assertNotNull(response);
    verify(userRepository, times(1)).save(mockedUser);
    verify(jwtService, times(1)).generateToken(mockedUser);
  }

  @Test
  void testAuthenticateUserSuccess() {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(
        "john@example.com", "password"
    );
    User mockedUser = mock(User.class);
    when(userRepository.findByEmail(authenticationRequest.email()))
        .thenReturn(java.util.Optional.of(mockedUser));
    when(jwtService.generateToken(mockedUser)).thenReturn("mockedJwtToken");

    AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

    assertNotNull(response);
    assertEquals("mockedJwtToken", response.token());
    verify(authenticationManager, times(1)).authenticate(any());
    verify(jwtService, times(1)).generateToken(mockedUser);
  }

  @Test
  void testUserNotFound() {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(
        "nonexistent@example.com", "password"
    );
    when(userRepository.findByEmail(authenticationRequest.email()))
        .thenReturn(java.util.Optional.empty());

    assertThrows(UsernameNotFoundException.class, () -> authenticationService.authenticate(authenticationRequest));
    verify(authenticationManager, times(1)).authenticate(any());
    verify(jwtService, never()).generateToken(any());
  }
}
