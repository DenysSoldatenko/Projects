package com.example.weatherbot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for a single data point in the weather API response.
 */
@Getter
@Setter
public class Data {

  @JsonProperty("app_temp")         // Map "app_temp" from JSON to "appTemp" in Java
  private double appTemp;           // Apparent temperature (e.g., 20.3)

  @JsonProperty("clouds")           // Map "clouds" from JSON to "clouds" in Java
  private int clouds;               // Cloud coverage percentage (e.g., 88)

  @JsonProperty("datetime")         // Map "datetime" from JSON to "datetime" in Java
  private String datetime;          // Date and time (e.g., "2024-11-06")

  @JsonProperty("sunrise_ts")        // Map "sunrise_ts" from JSON to "sunrise" in Java
  private long sunrise;              // Sunrise time (e.g., "11:43")

  @JsonProperty("sunset_ts")         // Map "sunset_ts" from JSON to "sunset" in Java
  private long sunset;               // Sunset time (e.g., "22:14")
}
