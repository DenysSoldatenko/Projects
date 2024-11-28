package com.example.securitysystem.services;

import com.example.securitysystem.dtos.RegistrationRequest;

/**
 * Service interface for managing user registration.
 *
 * <p>This interface provides methods for registering users
 * and confirming their registration tokens.</p>
 */
public interface RegistrationService {

  /**
   * Registers a new user with the provided details.
   *
   * @param request The registration request containing the user's details.
   * @return A message confirming the registration status.
   */
  String register(RegistrationRequest request);

  /**
   * Confirms the user's registration by verifying the provided token.
   *
   * @param token The token used to confirm the user's registration.
   * @return A message indicating the token verification status.
   */
  String confirmToken(String token);
}
