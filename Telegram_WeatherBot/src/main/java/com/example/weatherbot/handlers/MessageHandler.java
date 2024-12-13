package com.example.weatherbot.handlers;

import com.example.weatherbot.services.WeatherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handler for processing messages received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {

  WeatherService weatherService;

  /**
   * Handles incoming bot API objects, processes them, and returns an appropriate response.
   * This method is specifically designed to process weather-related requests.
   *
   * @param object the incoming BotApiObject (in this case, expected to be a Message).
   * @return a BotApiMethod containing the response message to be sent.
   * @throws ClassCastException if the object is not a Message type.
   */
  public BotApiMethod<?> handle(BotApiObject object) {
    var message = (Message) object;
    return weatherService.processWeatherRequest(message.getChatId(), message.getText());
  }
}