package com.example.jwtauthsecuritysystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for user registration requests.
 */
@Schema(description = "Data Transfer Object (DTO) for user registration requests")
public record RegisterRequest(

    @Schema(
      description = "First name of the user",
      example = "John"
    )
    String firstname,

    @Schema(
      description = "Last name of the user",
      example = "Doe"
    )
    String lastname,

    @Schema(
      description = "Email address of the user",
      example = "john.doe@example.com"
    )
    String email,

    @Schema(
      description = "Password for the user account",
      example = "securePassword123"
    )
    String password
) {
}