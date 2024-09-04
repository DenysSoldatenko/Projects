package com.example.springsecuritysystem.controllers;

import static org.springframework.http.ResponseEntity.ok;

import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import com.example.springsecuritysystem.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
    name = "Authentication Controller",
    description = "APIs for user authentication and token generation"
)
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Authenticates a user and generates a JWT token upon successful authentication.
   *
   * @param request The authentication request containing email and password.
   * @return ResponseEntity with a JWT token if authentication is successful, or an error response.
   */
  @Operation(
      summary = "Authenticate user",
      description = "Authenticates a user with email and password, and returns a JWT token if successful"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "Successfully authenticated and returned the JWT token"
      ),
      @ApiResponse(
        responseCode = "403",
        description = "Forbidden: Invalid credentials or access denied"
      )
  })
  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
    String token = authenticationService.authenticateAndGenerateToken(request);
    return ok(token);
  }
}
