package com.example.springsecuritysystem.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing an authentication request.
 */
@Schema(description = "DTO representing an authentication request containing the user's email and password")
public record AuthenticationRequest(

    @Schema(
      description = "User's email address",
      example = "john.doe@gmail.com"
    )
    String email,

    @Schema(
      description = "User's password",
      example = "password"
    )
    String password
) {}
