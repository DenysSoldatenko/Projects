package com.example.jwtauthsecuritysystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for authentication requests.
 */
@Schema(description = "Data Transfer Object (DTO) for authentication requests")
public record AuthenticationRequest(

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