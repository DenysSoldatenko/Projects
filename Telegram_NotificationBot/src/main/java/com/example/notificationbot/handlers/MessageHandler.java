package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.managers.NotificationManager;
import com.example.notificationbot.repositories.UserRepository;
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
public class MessageHandler implements BaseHandler {

  UserRepository userRepository;
  NotificationManager notificationManager;

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot bot) {
    var message = (Message) object;
    var user = userRepository.findByChatId(message.getChatId());

    return switch (user.getAction()) {
      case SENDING_TIME, SENDING_DESCRIPTION, SENDING_TITLE -> notificationManager.processMessage(message, bot);
      default -> throw new UnsupportedOperationException("Unexpected value: " + user.getAction());
    };
  }
}