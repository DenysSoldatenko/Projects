package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an address with country, city, and postal code information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an address with country, city, and postal code information")
public class Address {

  @Schema(
      description = "The country of the address",
      example = "United States"
  )
  private String country;

  @Schema(
      description = "The city of the address",
      example = "New York"
  )
  private String city;

  @Schema(
      description = "The postal code of the address",
      example = "10001"
  )
  private String postCode;
}