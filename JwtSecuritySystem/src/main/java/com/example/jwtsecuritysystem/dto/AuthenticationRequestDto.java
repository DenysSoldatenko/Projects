package com.example.jwtsecuritysystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing an authentication request.
 */
@Schema(description = "DTO representing an authentication request, containing username and password")
public record AuthenticationRequestDto(

    @Schema(
      description = "Username of the user",
      example = "regularUser123@gmail.com"
    )
    String username,

    @Schema(
      description = "Password of the user",
      example = "password123"
    )
    String password
) {
}