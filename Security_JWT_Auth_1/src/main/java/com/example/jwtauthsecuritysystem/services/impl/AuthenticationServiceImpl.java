package com.example.jwtauthsecuritysystem.services.impl;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.models.User;
import com.example.jwtauthsecuritysystem.repositories.UserRepository;
import com.example.jwtauthsecuritysystem.security.JwtService;
import com.example.jwtauthsecuritysystem.services.AuthenticationService;
import com.example.jwtauthsecuritysystem.utils.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link AuthenticationService} interface.
 *
 * <p>This service handles user registration and authentication operations,
 * including generating JWT tokens for authenticated users.</p>
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final UserFactory userFactory;

  /**
   * Registers a new user based on the provided registration request.
   *
   * @param request The registration request containing user details.
   * @return AuthenticationResponse containing the JWT token.
   */
  @Override
  public AuthenticationResponse registerUser(RegisterRequest request) {
    User user = userFactory.createUserFromRequest(request);
    userRepository.save(user);

    return createAuthenticationResponse(user);
  }

  /**
   * Authenticates a user based on the provided authentication request.
   *
   * @param request The authentication request containing user credentials.
   * @return AuthenticationResponse containing the JWT token.
   */
  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticateUser(request);

    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    return createAuthenticationResponse(user);
  }

  private void authenticateUser(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
  }

  private AuthenticationResponse createAuthenticationResponse(User user) {
    String jwtToken = jwtService.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }
}
