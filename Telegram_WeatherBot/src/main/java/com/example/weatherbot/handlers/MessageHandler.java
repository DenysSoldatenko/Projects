package com.example.weatherbot.handlers;

import com.example.weatherbot.utils.WeatherService;
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

  public BotApiMethod<?> handle(BotApiObject object) {
    var message = (Message) object;
    return weatherService.processWeatherRequest(message.getChatId(), message.getText());
  }
}