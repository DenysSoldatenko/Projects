package com.example.weatherbot.handlers;

import com.example.weatherbot.configurations.TelegramBot;
import com.example.weatherbot.managers.MainManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handler for processing commands received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {

  MainManager mainManager;

  public BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot) {
    var command = (Message) object;
    if (command.getText().equals("/start")) {
      return mainManager.processCommand(command);
    }
    throw new UnsupportedOperationException("Method not implemented for command: " + command.getText());
  }
}