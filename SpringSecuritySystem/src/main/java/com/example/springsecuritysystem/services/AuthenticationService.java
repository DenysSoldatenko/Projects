package com.example.springsecuritysystem.services;

import com.example.springsecuritysystem.dtos.AuthenticationRequest;

/**
 * Service interface for handling authentication and token generation.
 */
public interface AuthenticationService {

  String authenticateAndGenerateToken(AuthenticationRequest request);
}
