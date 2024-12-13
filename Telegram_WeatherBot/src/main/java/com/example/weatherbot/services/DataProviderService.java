package com.example.weatherbot.services;

import com.example.weatherbot.configurations.WeatherProperties;
import com.example.weatherbot.dtos.WeatherResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service to interact with weather APIs and retrieve weather information for a given city.
 * Provides methods to fetch hourly and daily weather forecasts.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataProviderService {

  RestTemplate restTemplate;
  WeatherProperties weatherProperties;

  /**
   * Fetches weather data for a specified time period (e.g., hours or days).
   *
   * @param baseUrl the base URL of the weather API (either hourly or daily).
   * @param cityName the name of the city to fetch weather data for.
   * @param timePeriodParam the query parameter indicating the time period type ("hours" or "days").
   * @param periodCount the number of hours or days to fetch data for.
   * @return a {@link WeatherResponse} object containing the weather data.
   */
  private WeatherResponse getWeatherDataForTimePeriod(String baseUrl, String cityName, String timePeriodParam, String periodCount) {
    String fullUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
        .queryParam("city", cityName)
        .queryParam("key", weatherProperties.getApiKey())
        .queryParam(timePeriodParam, periodCount)
        .toUriString();

    return restTemplate.getForObject(fullUrl, WeatherResponse.class);
  }

  /**
   * Retrieves hourly weather forecast for the given city.
   *
   * @param cityName the name of the city to fetch hourly weather data for.
   * @param hours the number of hours of weather data to retrieve (typically 24 for 1-day forecast).
   * @return a {@link WeatherResponse} object containing the hourly weather data.
   */
  public WeatherResponse getHourlyWeatherByCity(String cityName, String hours) {
    return getWeatherDataForTimePeriod(weatherProperties.getBaseHourlyUrl(), cityName, "hours", hours);
  }

  /**
   * Retrieves daily weather forecast for the given city.
   *
   * @param cityName the name of the city to fetch daily weather data for.
   * @param days the number of days of weather data to retrieve (typically 7 for a 7-day forecast).
   * @return a {@link WeatherResponse} object containing the daily weather data.
   */
  public WeatherResponse getDailyWeatherByCity(String cityName, String days) {
    return getWeatherDataForTimePeriod(weatherProperties.getBaseDailyUrl(), cityName, "days", days);
  }
}
