package com.example.springsecuritysystem.services;

import com.example.springsecuritysystem.dtos.AuthenticationRequest;

/**
 * Service interface for handling authentication and token generation.
 */
public interface AuthenticationService {

  /**
   * Authenticates the user with the provided credentials and generates a JWT token.
   *
   * <p>This method validates the user credentials and, if successful, generates a token
   * that can be used for subsequent authenticated requests.</p>
   *
   * @param request The {@link AuthenticationRequest} containing the user's credentials
   *                (e.g., username/email and password).
   * @return A JWT token string representing the authenticated user.
   */
  String authenticateAndGenerateToken(AuthenticationRequest request);
}
