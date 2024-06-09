package com.example.securitysystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a registration request for a new user.
 */
@Schema(description = "Request payload for user registration")
public record RegistrationRequest(

    @Schema(
      description = "First name of the user",
      example = "John"
    )
    String firstName,

    @Schema(
      description = "Last name of the user",
      example = "Doe"
    )
    String lastName,

    @Schema(
      description = "Email address of the user",
      example = "john.doe@example.com"
    )
    String email,

    @Schema(
      description = "Password for the user account",
      example = "P@ssw0rd"
    )
    String password
) {
}