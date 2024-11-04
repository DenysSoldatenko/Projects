package com.example.notificationbot.managers.message;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.factories.NotificationMarkupFactory;
import com.example.notificationbot.factories.NotificationMessageFactory;
import com.example.notificationbot.repositories.NotificationRepository;
import com.example.notificationbot.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Manages messages related to notifications in the Telegram bot.
 * This class handles user interactions through messages and updates
 * the chat interface accordingly.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageManagerImpl implements MessageManager {

  UserRepository userRepository;
  NotificationRepository notificationRepository;
  NotificationMarkupFactory notificationMarkupFactory;
  NotificationMessageFactory notificationMessageFactory;

  @Override
  public BotApiMethod<?> showMainMenu(Message message, TelegramBot bot) {
    var user = userRepository.findByChatId(message.getChatId());
    var notification = notificationRepository.findNotificationById(user.getCurrentNotification()).orElseThrow();

    return notificationMessageFactory.createMessageResponse(
      message,
      "Configure the notification",
      notificationMarkupFactory.setNotificationMarkup(notification)
    );
  }
}
