package com.example.notificationbot.utlis;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.entities.NotificationStatus;
import com.example.notificationbot.repositories.NotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Manages the sending of notifications through a Telegram bot.
 * This class implements Runnable, allowing it to be executed in a separate thread.
 * It handles the notification delay, sends the notification message, and updates the
 * notification status in the repository.
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationContainer implements Runnable {

  Long chatId;
  TelegramBot bot;
  Notification notification;
  NotificationRepository notificationRepo;

  @Override
  public void run() {
    sleepForNotificationDuration();
    sendNotification();
    completeNotification();
  }

  private void sleepForNotificationDuration() {
    try {
      Thread.sleep(notification.getDuration() * 1000L);
    } catch (InterruptedException e) {
      log.error("Notification delay interrupted: {}", e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  private void sendNotification() {
    SendMessage message = SendMessage.builder()
        .chatId(chatId)
        .text(buildNotificationText(notification))
        .build();

    try {
      bot.execute(message);
    } catch (TelegramApiException e) {
      log.error("Failed to send notification: {}", e.getMessage());
    }
  }

  private void completeNotification() {
    notification.setStatus(NotificationStatus.COMPLETED);
    notificationRepo.save(notification);
  }

  private String buildNotificationText(Notification notification) {
    return String.format("⚡️ Reminder: %s\n❗️ %s\n\n", notification.getTitle(), notification.getDescription());
  }
}
