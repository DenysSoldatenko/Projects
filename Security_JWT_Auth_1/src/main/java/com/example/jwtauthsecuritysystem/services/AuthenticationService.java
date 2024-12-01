package com.example.jwtauthsecuritysystem.services;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;

/**
 * Service interface for handling authentication operations, including user registration
 * and login.
 *
 * <p>This interface provides methods for registering a new user and authenticating an
 * existing user. Both operations return an authentication response containing a JWT token
 * upon success.</p>
 */
public interface AuthenticationService {

  /**
   * Registers a new user and returns an authentication response with a JWT token.
   *
   * @param request The registration request containing user details (e.g., username, email,
   *                password).
   * @return The authentication response containing the JWT token for the newly registered user.
   */
  AuthenticationResponse registerUser(RegisterRequest request);

  /**
   * Authenticates an existing user based on provided credentials and returns a JWT token.
   *
   * @param request The authentication request containing user credentials (e.g., username/email
   *                and password).
   * @return The authentication response containing the JWT token if authentication is successful.
   */
  AuthenticationResponse authenticate(AuthenticationRequest request);
}
