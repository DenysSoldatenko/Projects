package com.example.weatherbot.factories;

import static com.example.weatherbot.data.CallbackData.weather_forecast;

import com.example.weatherbot.keyboards.KeyboardFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Factory class responsible for creating various markup elements for the Telegram bot.
 * This includes inline keyboards used in bot interactions, like buttons for weather forecasts.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MarkupFactory {

  KeyboardFactory keyboardFactory;

  /**
   * Creates a button markup for viewing the weather forecast.
   *
   * @return an InlineKeyboardMarkup containing a "View Weather" button
   */
  public InlineKeyboardMarkup createWeatherForecastButtonMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("View Weather"),
      List.of(1),
      List.of(weather_forecast.name())
    );
  }
}
