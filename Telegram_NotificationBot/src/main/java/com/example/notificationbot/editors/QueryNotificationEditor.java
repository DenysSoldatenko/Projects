package com.example.notificationbot.editors;

import static com.example.notificationbot.entities.NotificationStatus.IN_PROGRESS;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Action;
import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.entities.NotificationStatus;
import com.example.notificationbot.factories.NotificationContainerFactory;
import com.example.notificationbot.factories.NotificationMarkupFactory;
import com.example.notificationbot.factories.NotificationMessageFactory;
import com.example.notificationbot.repositories.NotificationRepository;
import com.example.notificationbot.repositories.UserRepository;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * A component for managing notifications within the Telegram bot.
 * It handles actions related to creating, editing, and sending notifications.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryNotificationEditor {

  UserRepository userRepository;
  NotificationRepository notificationRepository;
  NotificationMarkupFactory notificationMarkupFactory;
  NotificationMessageFactory notificationMessageFactory;
  NotificationContainerFactory notificationContainerFactory;

  /**
   * Processes a notification request and sends the notification if valid.
   *
   * @param query The callback query from Telegram.
   * @param id    The ID of the notification to be processed.
   * @param bot   The Telegram bot instance to send messages.
   * @return A response indicating the result of the operation.
   * @throws TelegramApiException If an error occurs while sending a message.
   */
  public BotApiMethod<?> processAndSendNotification(CallbackQuery query, String id, TelegramBot bot) throws TelegramApiException {
    var notification = notificationRepository.findById(UUID.fromString(id)).orElseThrow();

    if (isNotificationValid(notification)) {
      bot.execute(notificationMessageFactory.createAnswerCallback(query, "The notification will come to you in " + notification.getDuration() + " seconds 👀"));
      updateNotificationStatus(notification);
      startNotificationThread(query, bot, notification);
      return notificationMessageFactory.createEditMessageResponse(
        query,
        "✅ Done!\n\n✨ Would you like to set another notification?",
        notificationMarkupFactory.createAddNotificationButtonMarkup()
      );
    }

    return notificationMessageFactory.createAnswerCallback(
      query,
      "Please fill in the required values: title and time"
    );
  }

  /**
   * Creates a new notification for the user and returns the response to configure it.
   *
   * @param query The callback query from Telegram.
   * @return A response prompting the user to configure the new notification.
   */
  public BotApiMethod<?> createNewNotification(CallbackQuery query) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    Notification notification = Notification.builder()
        .user(user)
        .status(NotificationStatus.PENDING)
        .build();
    Notification savedNotification = notificationRepository.save(notification);

    return notificationMessageFactory.createEditMessageResponse(
      query,
      "Set up your new notification",
      notificationMarkupFactory.setNotificationMarkup(savedNotification)
    );
  }

  /**
   * Edits the page for a specific notification.
   *
   * @param query The callback query from Telegram containing the user's action.
   * @param id The ID of the notification to edit, formatted as a UUID string.
   * @return A BotApiMethod object that updates the message with the notification details.
   */
  public BotApiMethod<?> editPage(CallbackQuery query, String id) {
    var notification = notificationRepository.findNotificationById(UUID.fromString(id)).orElseThrow();
    return notificationMessageFactory.createEditMessageResponse(
      query,
      "Update your notification details",
      notificationMarkupFactory.setNotificationMarkup(notification)
    );
  }

  /**
   * Initiates the process of editing the title of the notification.
   *
   * @param query The callback query from Telegram.
   * @param id    The ID of the notification to edit.
   * @return A response prompting the user to enter a title.
   */
  public BotApiMethod<?> editTitle(CallbackQuery query, String id) {
    setUserAction(query, id, Action.SENDING_TITLE);
    String text = "✏ Please write a simple title for your reminder";
    return notificationMessageFactory.createEditMessageResponse(query, text, notificationMarkupFactory.createBackButtonMarkup(id));
  }

  /**
   * Initiates the process of editing the duration of the notification.
   *
   * @param query The callback query from Telegram.
   * @param id    The ID of the notification to edit.
   * @return A response prompting the user to set the reminder time.
   */
  public BotApiMethod<?> editDuration(CallbackQuery query, String id) {
    setUserAction(query, id, Action.SENDING_TIME);
    String text = "⏰ Please set a reminder time\n\nFormat: HH:MM:SS (e.g., 01:30:00 for 1.5 hours)";
    return notificationMessageFactory.createEditMessageResponse(query, text, notificationMarkupFactory.createBackButtonMarkup(id));
  }

  /**
   * Initiates the process of editing the description of the notification.
   *
   * @param query The callback query from Telegram.
   * @param id    The ID of the notification to edit.
   * @return A response prompting the user to enter a description.
   */
  public BotApiMethod<?> editDescription(CallbackQuery query, String id) {
    setUserAction(query, id, Action.SENDING_DESCRIPTION);
    String text = "📝 Please enter the description you want for your notification";
    return notificationMessageFactory.createEditMessageResponse(query, text, notificationMarkupFactory.createBackButtonMarkup(id));
  }

  private boolean isNotificationValid(Notification notification) {
    return notification.getTitle() != null && !notification.getTitle().isBlank() && notification.getDuration() != null;
  }

  private void updateNotificationStatus(Notification notification) {
    notification.setStatus(IN_PROGRESS);
    notificationRepository.save(notification);
  }

  private void startNotificationThread(CallbackQuery query, TelegramBot telegramBot, Notification notification) {
    Thread.startVirtualThread(
        notificationContainerFactory.createNotificationContainer(query.getMessage().getChatId(), telegramBot, notification)
    );
  }

  private void setUserAction(CallbackQuery query, String id, Action action) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    user.setAction(action);
    user.setCurrentNotification(UUID.fromString(id));
    userRepository.save(user);
  }
}
