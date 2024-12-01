package com.example.jwtauthsecuritysystem.controllers;

import com.example.jwtauthsecuritysystem.dtos.AuthenticationRequest;
import com.example.jwtauthsecuritysystem.dtos.AuthenticationResponse;
import com.example.jwtauthsecuritysystem.dtos.RegisterRequest;
import com.example.jwtauthsecuritysystem.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication operations, such as user registration and login.
 *
 * <p>This class provides endpoints for registering a new user and authenticating an existing user.
 * It responds with JWT tokens upon successful registration or authentication.</p>
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
    name = "Authentication Controller",
    description = "Controller for handling authentication-related operations"
)
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Registers a new user and returns an authentication response containing the JWT token.
   *
   * @param request The registration request containing user details.
   * @return The authentication response containing the JWT token for the newly registered user.
   */
  @PostMapping("/register")
  @Operation(
      summary = "Register a new user",
      description = "Registers a new user and returns an authentication response containing the JWT token",
      responses = {
          @ApiResponse(
            responseCode = "200",
            description = "User successfully registered",
            content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AuthenticationResponse.class)
            )
          ),
          @ApiResponse(responseCode = "400", description = "Invalid registration request")
      }
  )
  public AuthenticationResponse register(
      @RequestBody @Parameter(description = "Registration request with user details")
      RegisterRequest request
  ) {
    return authenticationService.registerUser(request);
  }

  /**
   * Authenticates a user with the provided credentials and returns a JWT token.
   *
   * @param request The authentication request containing user credentials (username and password).
   * @return The authentication response containing the JWT token if authentication is successful.
   */
  @PostMapping("/authenticate")
  @Operation(
      summary = "Authenticate a user",
      description = "Authenticates a user with provided credentials and returns a JWT token",
      responses = {
          @ApiResponse(
            responseCode = "200",
            description = "User successfully authenticated",
            content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AuthenticationResponse.class)
            )
          ),
          @ApiResponse(responseCode = "401", description = "Invalid credentials")
      }
  )
  public AuthenticationResponse authenticate(
      @RequestBody @Parameter(description = "Authentication request with user credentials")
      AuthenticationRequest request
  ) {
    return authenticationService.authenticate(request);
  }
}
