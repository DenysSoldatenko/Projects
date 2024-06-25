package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.AdminDto;
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
 * Controller class for handling admin-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/admin")
@Tag(name = "Admin Controller", description = "Endpoints for managing admin users")
public class AdminController {

  private final UserService userService;

  /**
   * Get a user by their unique identifier.
   *
   * @param id The unique identifier of the user to retrieve.
   * @return A ResponseEntity containing the user information if found (HttpStatus.OK),
   *     or HttpStatus.NO_CONTENT if not found.
   */
  @Operation(
      summary = "Get an admin by ID",
      description = "Retrieve the details of an admin using their unique identifier"
  )
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200",
        description = "Admin found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = AdminDto.class)
        )
      ),
      @ApiResponse(
        responseCode = "204",
        description = "No Content - Admin not found",
        content = @Content
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error - Unexpected error occurred",
        content = @Content
      )
  })
  @GetMapping(value = "/{id}")
  public AdminDto getUserById(@PathVariable(name = "id") Long id) {
    return userService.getAdminById(id);
  }
}