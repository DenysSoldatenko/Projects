package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import java.util.Map;

/**
 * Service interface for handling authentication operations.
 */
public interface AuthenticationService {

  /**
   * Authenticates a user based on the provided credentials.
   *
   * @param requestDto Contains the login credentials (e.g., username and password).
   * @return A map containing the authentication tokens (e.g., JWT token)
   *     or error message if authentication fails.
   */
  Map<Object, Object> login(AuthenticationRequestDto requestDto);
}
