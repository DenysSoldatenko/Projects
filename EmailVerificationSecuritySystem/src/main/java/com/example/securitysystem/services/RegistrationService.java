package com.example.securitysystem.services;

import com.example.securitysystem.dtos.RegistrationRequest;

/**
 * Service interface for managing user registration.
 *
 * <p>This interface defines methods for registering users and confirming registration tokens.</p>
 */
public interface RegistrationService {

  String register(RegistrationRequest request);

  String confirmToken(String token);
}
