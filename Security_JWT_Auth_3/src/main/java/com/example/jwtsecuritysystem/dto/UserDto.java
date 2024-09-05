package com.example.jwtsecuritysystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a User.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Data Transfer Object that represents a user in the system")
public class UserDto {

  @Schema(
      description = "Unique identifier of the user",
      example = "1"
  )
  private Long id;

  @Schema(
      description = "Username of the user",
      example = "regularUser123@gmail.com"
    )
  private String username;

  @Schema(
      description = "First name of the user",
      example = "Jane"
  )
  private String firstName;

  @Schema(
      description = "Last name of the user",
      example = "Doe"
  )
  private String lastName;
}