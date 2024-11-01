package com.example.notificationbot.services;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.handlers.CallbackQueryHandler;
import com.example.notificationbot.handlers.CommandHandler;
import com.example.notificationbot.handlers.MessageHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

  MessageHandler messageHandler;
  CommandHandler commandHandler;
  CallbackQueryHandler callbackQueryHandler;

  public BotApiMethod<?> distribute(@RequestBody Update update, TelegramBot telegramBot) {
    if (update.hasCallbackQuery()) {
      return callbackQueryHandler.answer(update.getCallbackQuery(), telegramBot);
    }

    if (update.hasMessage()) {
      var message = update.getMessage();
      if (message.hasText()) {
        if (message.getText().charAt(0) == '/') {
          return commandHandler.answer(message, telegramBot);
        }
        return messageHandler.answer(message, telegramBot);
      }
    }

    log.warn("Not supported yet - {}", update);
    return null;
  }
}
