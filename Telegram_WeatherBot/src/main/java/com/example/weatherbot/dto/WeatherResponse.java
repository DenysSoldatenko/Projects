package com.example.weatherbot.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the Weather API response.
 */
@Getter
@Setter
public class WeatherResponse {
  private List<Data> data;
}
