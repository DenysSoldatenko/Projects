package com.example.militarytrackerbot.dtos;

import lombok.Data;

/**
 * DTO representing the response structure for multiple days of military data.
 *
 * <p>This class holds the response message and a {@link DataDto} object, which contains
 * a list of records for the specified period (e.g., for a week or month).
 */
@Data
public class MultipleDaysDataDto {
  private String message;
  private DataDto data;
}