package com.example.notificationbot.factories;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.repositories.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

/**
 * Factory for creating instances of NotificationContainer.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationContainerFactory {

  NotificationRepository notificationRepository;
  NotificationMessageFactory notificationMessageFactory;

  /**
   * Creates a new instance of NotificationContainer.
   *
   * @param chatId       the chat ID to send notifications to
   * @param notification the Notification object to process
   * @return a new instance of NotificationContainer
   */
  public NotificationContainer createNotificationContainer(Long chatId, TelegramBot telegramBot, Notification notification) {
    return new NotificationContainer(
      chatId,
      telegramBot,
      notification,
      notificationRepository,
      notificationMessageFactory
    );
  }
}
