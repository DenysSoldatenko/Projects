package com.example.jwtauthsecuritysystem.services;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;

/**
 * Service interface for handling authentication operations.
 *
 * <p>This interface defines methods for user registration and authentication.</p>
 */
public interface AuthenticationService {

  AuthenticationResponse registerUser(RegisterRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);
}
