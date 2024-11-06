package com.example.weatherbot.configurations;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for managing Weather API properties.
 */
@Data
@Configuration
public class WeatherProperties {

  @Value("${weather.base-hourly-url}")
  private String baseHourlyUrl;

  @Value("${weather.base-daily-url}")
  private String baseDailyUrl;

  @Value("${weather.api-key}")
  private String apiKey;
}
