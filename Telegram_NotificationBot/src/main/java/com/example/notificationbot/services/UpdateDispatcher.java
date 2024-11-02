package com.example.notificationbot.services;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.handlers.CommandHandler;
import com.example.notificationbot.handlers.MessageHandler;
import com.example.notificationbot.handlers.QueryHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Service for dispatching incoming updates from the Telegram bot.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

  MessageHandler messageHandler;
  CommandHandler commandHandler;
  QueryHandler queryHandler;

  public BotApiMethod<?> processUpdate(@RequestBody Update update, TelegramBot telegramBot) {
    if (update.hasCallbackQuery()) {
      return queryHandler.handle(update.getCallbackQuery(), telegramBot);
    }

    if (update.hasMessage()) {
      var message = update.getMessage();
      if (message.hasText()) {
        if (message.getText().charAt(0) == '/') {
          return commandHandler.handle(message, telegramBot);
        }
        return messageHandler.handle(message, telegramBot);
      }
    }

    log.warn("Not supported yet - {}", update);
    return null;
  }
}
