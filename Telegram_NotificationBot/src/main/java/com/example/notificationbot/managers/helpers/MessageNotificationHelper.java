package com.example.notificationbot.managers.helpers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Action;
import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.entities.User;
import com.example.notificationbot.keyboards.KeyboardFactory;
import com.example.notificationbot.managers.message.MessageManager;
import com.example.notificationbot.repositories.NotificationRepository;
import com.example.notificationbot.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static com.example.notificationbot.data.CallbackData.notification_back_;

/**
 * Utility class for handling notification-related operations.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageNotificationHelper {

  UserRepository userRepository;
  MessageManager messageManager;
  KeyboardFactory keyboardFactory;
  NotificationRepository notificationRepository;

  /**
   * Edits the title of the current notification for the given user.
   *
   * @param message the message containing the new title
   * @param user the user whose notification is being edited
   * @param bot the Telegram bot instance
   * @return the BotApiMethod response to show the main menu
   */
  public BotApiMethod<?> editTitle(Message message, User user, TelegramBot bot) {
    Notification notification = notificationRepository.findById(user.getCurrentNotification()).orElseThrow();
    notification.setTitle(message.getText());
    notificationRepository.save(notification);

    user.setAction(Action.FREE);
    userRepository.save(user);
    return messageManager.showMainMenu(message, bot);
  }

  /**
   * Edits the description of the current notification for the given user.
   *
   * @param message the message containing the new description
   * @param user the user whose notification is being edited
   * @param bot the Telegram bot instance
   * @return the BotApiMethod response to show the main menu
   */
  public BotApiMethod<?> editDescription(Message message, User user, TelegramBot bot) {
    var notification = notificationRepository.findById(user.getCurrentNotification()).orElseThrow();
    notification.setDescription(message.getText());
    notificationRepository.save(notification);

    user.setAction(Action.FREE);
    userRepository.save(user);
    return messageManager.showMainMenu(message, bot);
  }

  /**
   * Edits the duration of the current notification based on the given time format.
   *
   * @param message the message containing the time in HH:MM:SS format
   * @param user the user whose notification is being edited
   * @param bot the Telegram bot instance
   * @return the BotApiMethod response to show the main menu or an error message
   */
  public BotApiMethod<?> editTime(Message message, User user, TelegramBot bot) {
    var notification = notificationRepository.findById(user.getCurrentNotification()).orElseThrow();

    String messageText = message.getText().strip();
    if (isValidTimeFormat(messageText)) {
      int seconds = convertTimeToSeconds(messageText);
      notification.setDuration(seconds);
    } else {
      return sendErrorMessage(message, user);
    }

    notificationRepository.save(notification);
    user.setAction(Action.FREE);
    userRepository.save(user);
    return messageManager.showMainMenu(message, bot);
  }

  private boolean isValidTimeFormat(String time) {
    return time.matches("^[0-9]{2}:[0-9]{2}:[0-9]{2}$");
  }

  private int convertTimeToSeconds(String time) {
    var nums = time.split(":");
    return Integer.parseInt(nums[0]) * 3600 + Integer.parseInt(nums[1]) * 60 + Integer.parseInt(nums[2]);
  }

  private BotApiMethod<?> sendErrorMessage(Message message, User user) {
    return SendMessage.builder()
      .text("Invalid input format\nHH:MM:SS (01:00:30 - one hour, zero minutes, thirty seconds)")
      .chatId(message.getChatId())
      .replyMarkup(keyboardFactory.createInlineKeyboard(
        List.of("\uD83D\uDD19 Back"),
        List.of(1),
        List.of(notification_back_ + String.valueOf(user.getCurrentNotification()))
      ))
      .build();
  }
}
