package com.example.weatherbot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for a single data point in the weather API response.
 */
@Getter
@Setter
public class Data {

  @JsonProperty("city_name")       // Map "city_name" from JSON to "cityName" in Java
  private String cityName;          // Name of the city (e.g., "Raleigh")

  @JsonProperty("timezone")        // Map "timezone" from JSON to "timezone" in Java
  private String timezone;          // Timezone (e.g., "America/New_York")

  @JsonProperty("app_temp")        // Map "app_temp" from JSON to "appTemp" in Java
  private double appTemp;           // Apparent temperature (e.g., 20.3)

  @JsonProperty("clouds")          // Map "clouds" from JSON to "clouds" in Java
  private int clouds;               // Cloud coverage percentage (e.g., 88)

  @JsonProperty("datetime")        // Map "datetime" from JSON to "datetime" in Java
  private String datetime;          // Date and time (e.g., "2024-11-06:14")

  @JsonProperty("sunrise")         // Map "sunrise" from JSON to "sunrise" in Java
  private String sunrise;           // Sunrise time (e.g., "11:43")

  @JsonProperty("sunset")          // Map "sunset" from JSON to "sunset" in Java
  private String sunset;            // Sunset time (e.g., "22:14")
}
