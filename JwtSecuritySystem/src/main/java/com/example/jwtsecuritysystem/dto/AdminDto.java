package com.example.jwtsecuritysystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an Admin user.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminDto {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String status;
}