package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
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
public class MessageHandler extends BaseHandler {

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot) {
    Message message = (Message) object;
    throw new UnsupportedOperationException("Method not implemented yet");
  }
}