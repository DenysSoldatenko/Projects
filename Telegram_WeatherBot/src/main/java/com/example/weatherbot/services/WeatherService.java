package com.example.weatherbot.services;

import static com.example.weatherbot.factories.MessageFactory.createMessageResponse;
import static com.example.weatherbot.utils.MessageUtils.INVALID_INPUT_MESSAGE;
import static com.example.weatherbot.utils.MessageUtils.WEATHER_FETCH_ERROR_MESSAGE;

import com.example.weatherbot.dtos.WeatherResponse;
import com.example.weatherbot.utils.ResponseFormatterUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * WeatherService is responsible for handling the weather requests from users, validating the input,
 * and retrieving weather data either hourly or daily for a given city.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WeatherService {

  static String REGEX = "^([A-Za-z\\s]+)\\s(\\d+)(h|d| hours| days)$";

  DataProviderService dataProviderService;

  /**
   * Processes a weather request by validating the input and fetching the appropriate weather data.
   *
   * @param chatId the chat ID of the user requesting the weather information
   * @param input  the user's input containing the city and weather period
   * @return a SendMessage object with the weather data or an error message
   */
  public SendMessage processWeatherRequest(Long chatId, String input) {
    String cleanedInput = input.trim();
    Matcher matcher = Pattern.compile(REGEX).matcher(cleanedInput);

    if (matcher.matches()) {
      String city = matcher.group(1).trim();
      String number = matcher.group(2);
      String timeUnit = matcher.group(3).trim();

      if (isValidWeatherRequest(city, timeUnit, number)) {
        return retrieveWeatherData(chatId, city, number, timeUnit);
      }
    }

    log.warn("Invalid format received: {}", input);
    return createMessageResponse(chatId, INVALID_INPUT_MESSAGE);
  }

  private boolean isValidWeatherRequest(String city, String timeUnit, String number) {
    return !city.isEmpty() && isValidTimePeriod(timeUnit) && isValidRequestNumber(number);
  }

  private boolean isValidTimePeriod(String timeUnit) {
    return timeUnit.matches("(?i)h|d|hours|days");
  }

  private boolean isValidRequestNumber(String number) {
    try {
      Integer.parseInt(number);
      log.info("Valid number received: {}", number);
      return true;
    } catch (NumberFormatException e) {
      log.warn("Invalid number received: {}", number);
      return false;
    }
  }

  private SendMessage retrieveWeatherData(Long chatId, String city, String number, String timeUnit) {
    WeatherResponse response = null;

    if (isDailyForecast(timeUnit)) {
      response = dataProviderService.getDailyWeatherByCity(city, number);
    } else if (isHourlyForecast(timeUnit)) {
      response = dataProviderService.getHourlyWeatherByCity(city, number);
    }

    if (response != null) {
      return createMessageResponse(chatId, ResponseFormatterUtils.formatWeatherResponse(response));
    }

    log.error("Error: Could not retrieve weather data for city: {}", city);
    return createMessageResponse(chatId, WEATHER_FETCH_ERROR_MESSAGE);
  }

  private boolean isDailyForecast(String timeUnit) {
    return timeUnit.equalsIgnoreCase("d") || timeUnit.equalsIgnoreCase("days");
  }

  private boolean isHourlyForecast(String timeUnit) {
    return timeUnit.equalsIgnoreCase("h") || timeUnit.equalsIgnoreCase("hours");
  }
}
