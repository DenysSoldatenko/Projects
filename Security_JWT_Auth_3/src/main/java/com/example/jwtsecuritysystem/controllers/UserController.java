package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
@Tag(name = "User Controller", description = "Endpoints for managing user accounts")
public class UserController {

  private final UserService userService;

  /**
   * Retrieves the details of a user by their unique ID.
   *
   * <p>This endpoint allows an administrator or authorized user
   * to fetch the data of a user using their unique identifier (ID).
   * The user details are returned as a {@link UserDto} object.</p>
   *
   * @param id The unique identifier of the user.
   * @return A {@link UserDto} object containing the user's details if found.
   */
  @Operation(
      summary = "Get a user by ID",
      description = "Retrieve the details of a user using their unique identifier"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "User found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = UserDto.class)
        )
      ),
      @ApiResponse(
        responseCode = "204",
        description = "No Content - User not found",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error - Unexpected error occurred",
        content = @Content
      )
  })
  @GetMapping(value = "/{id}")
  public UserDto getUserById(@PathVariable(name = "id") Long id) {
    return userService.getById(id);
  }
}