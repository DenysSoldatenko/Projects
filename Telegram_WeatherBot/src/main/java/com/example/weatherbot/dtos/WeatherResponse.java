package com.example.weatherbot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the Weather API response.
 */
@Getter
@Setter
public class WeatherResponse {

  @JsonProperty("city_name")       // Map "city_name" from JSON to "cityName" in Java
  private String cityName;         // Name of the city (e.g., "Raleigh")

  @JsonProperty("timezone")        // Map "timezone" from JSON to "timezone" in Java
  private String timezone;         // Timezone (e.g., "America/New_York")

  private List<Data> data;
}
