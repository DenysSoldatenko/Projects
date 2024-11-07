package com.example.weatherbot.managers;

import com.example.weatherbot.factories.MessageFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

        To get the weather forecast, please enter the city name followed by the forecast mode:

        🌍 Example 1: "London 24h" — Get the weather forecast for the next 24 hours in London
        🌍 Example 2: "Paris 5d" — Get the weather forecast for the next 5 days in Paris

        📍 Type a city and the forecast mode (e.g., '24h' for hourly or '5d' for daily).

        Enjoy and stay updated with the weather! 😊
      """;

  MessageFactory messageFactory;

  public BotApiMethod<?> processCommand(Message message) {
    return messageFactory.createMessageResponse(message.getChatId(), WELCOME_MESSAGE_TEMPLATE);
  }
}
