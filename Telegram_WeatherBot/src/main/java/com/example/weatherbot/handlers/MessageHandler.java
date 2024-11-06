package com.example.weatherbot.handlers;

import com.example.weatherbot.configurations.TelegramBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

/**
 * Handler for processing messages received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler implements BaseHandler {

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot bot) {

    return null;
  }
}