package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.services.AuthenticationService;
import com.example.jwtsecuritysystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user authentication operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@Tag(
    name = "Authentication Controller",
    description = "Endpoints for user authentication and management"
)
public class AuthenticationController {

  private final UserService userService;
  private final AuthenticationService authenticationService;

  /**
   * Authenticates a user with their username and password, and returns an authentication token.
   *
   * @param requestDto The authentication request containing
   *                   the user's credentials (username and password).
   * @return A map containing the authentication token if the credentials are valid.
   */
  @Operation(
      summary = "Authenticate user and generate token",
      description = "Authenticate a user with username and password and return an authentication token"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "Authentication successful",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorized - Invalid credentials",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error - Unexpected error occurred",
        content = @Content
      )
  })
  @PostMapping("/login")
  public Map<Object, Object> login(@RequestBody AuthenticationRequestDto requestDto) {
    return authenticationService.login(requestDto);
  }

  /**
   * Creates a new admin user.
   *
   * @param user The user details to be used for creating a new admin.
   * @return The created admin user as a DTO.
   */
  @Operation(
      summary = "Create a new admin user",
      description = "Register a new user with admin role"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "Admin user created successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = AdminDto.class)
        )
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad Request - Invalid input data",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error - Unexpected error occurred",
        content = @Content
      )
  })
  @PostMapping(value = "/createAdmin")
  public AdminDto createAdmin(@RequestBody User user) {
    return userService.createAdmin(user);
  }

  /**
   * Creates a new regular user.
   *
   * @param user The user details to be used for creating a new user.
   * @return The created user as a DTO.
   */
  @Operation(
      summary = "Create a new user",
      description = "Register a new user with regular role"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "User created successfully",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDto.class)
        )
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad Request - Invalid input data",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error - Unexpected error occurred",
        content = @Content
      )
  })
  @PostMapping(value = "/createUser")
  public UserDto createUser(@RequestBody User user) {
    return userService.createUser(user);
  }
}