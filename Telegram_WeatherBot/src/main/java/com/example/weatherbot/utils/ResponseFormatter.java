package com.example.weatherbot.utils;

import com.example.weatherbot.dtos.Data;
import com.example.weatherbot.dtos.WeatherResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

/**
 * Formats weather response data into a user-friendly string with emojis.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResponseFormatter {

  static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  /**
   * Formats the given WeatherResponse into a string with emojis.
   *
   * @param weatherResponse the weather response to format.
   * @return a formatted string containing weather information with emojis.
   */
  public String formatWeatherResponse(WeatherResponse weatherResponse) {
    List<Data> dataList = weatherResponse.getData();
    if (dataList == null || dataList.isEmpty()) {
      return "🚨 No weather data available!";
    }

    String cityInfo = String.format("🌆 City: %s\n🕰️ Timezone: %s\n\n",
        weatherResponse.getCityName(), weatherResponse.getTimezone());

    ZoneId timezone = ZoneId.of(weatherResponse.getTimezone());
    StringBuilder weatherDetails = new StringBuilder(cityInfo);

    dataList.forEach(data -> weatherDetails.append(formatCityWeather(data, timezone)));

    return weatherDetails.toString();
  }

  /**
   * Formats weather information for a specific city.
   *
   * @param data the Data object containing the weather data.
   * @param timezone the timezone to convert timestamps into.
   * @return a formatted string for the city's weather data with emojis.
   */
  private String formatCityWeather(Data data, ZoneId timezone) {
    return String.format(
      "📅 Date: %s\n🌡️ Temperature: %.1f°C\n☁️ Cloud Coverage: %d%%\n🌅 Sunrise: %s\n🌇 Sunset: %s\n\n",
      data.getDatetime(), data.getAppTemp(), data.getClouds(),
      formatTimestampToTime(data.getSunrise(), timezone),
      formatTimestampToTime(data.getSunset(), timezone)
    );
  }

  /**
   * Converts a UNIX timestamp to a formatted time string (HH:mm:ss).
   *
   * @param timestamp the UNIX timestamp.
   * @param zoneId the timezone to convert the timestamp to.
   * @return the formatted time string.
   */
  private String formatTimestampToTime(long timestamp, ZoneId zoneId) {
    return TIME_FORMATTER.withZone(zoneId).format(Instant.ofEpochSecond(timestamp));
  }
}
