package com.example.jwtauthsecuritysystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for authentication responses.
 */
@Schema(description = "Data Transfer Object (DTO) for authentication responses.")
public record AuthenticationResponse(

    @Schema(
      description = "JWT token used for authentication",
      example = "eyJhbGciOiJIUzI1NiI..."
    )
    String token
) {
}