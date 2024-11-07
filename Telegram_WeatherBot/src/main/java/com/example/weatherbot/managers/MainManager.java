package com.example.weatherbot.managers;

import com.example.weatherbot.factories.MarkupFactory;
import com.example.weatherbot.factories.MessageFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {

  static final String WELCOME_MESSAGE_TEMPLATE = """
        👋 Welcome to the Weather Forecast Bot!

        ✅ This bot was created for demonstration purposes
        ✅ It uses the WeatherBit API
        ✅ Technologies used: Java & Spring Boot

        Click the button below to view the weather forecast. Enjoy!
      """;

  MarkupFactory markupFactory;
  MessageFactory messageFactory;

  /**
   * Processes a command message and responds with a welcome message and a weather forecast button.
   *
   * @param message The incoming message object from the user.
   * @return A BotApiMethod response that contains the welcome message
   *     and inline keyboard with a "View Weather" button.
   */
  public BotApiMethod<?> processCommand(Message message) {
    return messageFactory.createMessageResponse(
      message,
      WELCOME_MESSAGE_TEMPLATE,
      markupFactory.createWeatherForecastButtonMarkup()
    );
  }

  /**
   * Processes a callback query (e.g., when a user clicks a button) and updates the message
   * with a welcome message and a weather forecast button.
   *
   * @param query The incoming callback query object from the user.
   * @return A BotApiMethod response that edits the existing message with a welcome message
   *     and the weather forecast button.
   */
  public BotApiMethod<?> processQuery(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      markupFactory.createWeatherForecastButtonMarkup()
    );
  }
}
