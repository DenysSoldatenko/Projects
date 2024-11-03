package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.managers.MainManager;
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
public class CommandHandler implements BaseHandler {

  MainManager mainManager;

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot) {
    var command = (Message) object;
    if (command.getText().equals("/start")) {
      return mainManager.processCommand(command, telegramBot);
    }
    throw new UnsupportedOperationException("Method not implemented yet!");
  }
}