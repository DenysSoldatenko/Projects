package com.example.springsecuritysystem.controllers;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import com.example.springsecuritysystem.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication-related requests.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Authenticates a user and generates a JWT token upon successful authentication.
   *
   * @param request The authentication request containing email and password.
   * @return ResponseEntity with a JWT token if authentication is successful, or an error response.
   */
  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
    String token = authenticationService.authenticateAndGenerateToken(request);
    return token != null ? ok(token) : status(400).body("Authentication failed!");
  }
}
