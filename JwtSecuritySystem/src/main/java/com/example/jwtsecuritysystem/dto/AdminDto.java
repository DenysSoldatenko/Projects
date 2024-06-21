package com.example.jwtsecuritysystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an Admin user.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Data Transfer Object that represents an admin user in the system")
public class AdminDto {

    @Schema(
      description = "Unique identifier of the admin user",
      example = "1"
    )
    private Long id;

    @Schema(
      description = "Username of the admin user",
      example = "adminUser123@gmail.com"
    )
    private String username;

    @Schema(
      description = "First name of the admin user",
      example = "John"
    )
    private String firstName;

    @Schema(
      description = "Last name of the admin user",
      example = "Doe"
    )
    private String lastName;

    @Schema(
      description = "Email address of the admin user",
      example = "john.doe@example.com"
    )
    private String email;

    @Schema(
      description = "Status of the admin user",
      example = "ACTIVE"
    )
    private String status;
}