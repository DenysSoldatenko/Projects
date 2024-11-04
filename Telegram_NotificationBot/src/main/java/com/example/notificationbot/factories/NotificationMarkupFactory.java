package com.example.notificationbot.factories;

import static com.example.notificationbot.data.CallbackData.main;
import static com.example.notificationbot.data.CallbackData.notification_back_;
import static com.example.notificationbot.data.CallbackData.notification_done_;
import static com.example.notificationbot.data.CallbackData.notification_edit_d_;
import static com.example.notificationbot.data.CallbackData.notification_edit_time_;
import static com.example.notificationbot.data.CallbackData.notification_edit_title_;
import static com.example.notificationbot.data.CallbackData.notification_main;
import static com.example.notificationbot.data.CallbackData.notification_new;

import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.keyboards.KeyboardFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * A utility class for editing the reply markup of notifications.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationMarkupFactory {

  KeyboardFactory keyboardFactory;

  /**
   * Creates an InlineKeyboardMarkup for a given notification, displaying its current status,
   * along with options to edit or confirm the notification.
   *
   * @param notification the notification object used to create the markup
   * @return an InlineKeyboardMarkup object representing the notification options
   *         with status indicators for title, duration, and description
   */
  public InlineKeyboardMarkup setNotificationMarkup(Notification notification) {
    List<String> text = List.of(
        getStatusText(notification.getTitle(), "Title"),
        getStatusText(notification.getDuration(), "Time"),
        getStatusText(notification.getDescription(), "Description"),
        "🔙 Main",
        "✔️ Confirm"
    );

    return keyboardFactory.createInlineKeyboard(
      text,
      List.of(2, 1, 2), // Button layout: 2 options in the first row, 1 in the second, 2 in the third
      List.of(
        notification_edit_title_.name() + notification.getId(),
        notification_edit_time_.name() + notification.getId(),
        notification_edit_d_.name() + notification.getId(),
        main.name(),
        notification_done_.name() + notification.getId()
      )
    );
  }

  /**
   * Creates a back button markup for navigating back to the previous menu or state.
   *
   * @param id the ID of the notification for the callback data
   * @return an InlineKeyboardMarkup containing a back button
   */
  public InlineKeyboardMarkup createBackButtonMarkup(String id) {
    return keyboardFactory.createInlineKeyboard(
      List.of("🔙 Back"),
      List.of(1),
      List.of(notification_back_ + id)
    );
  }

  /**
   * Creates a main button markup for navigating to the main menu.
   *
   * @return an InlineKeyboardMarkup containing a main menu button
   */
  public InlineKeyboardMarkup createMainButtonMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("🏠 Go to Main"),
      List.of(1),
      List.of(main.name())
    );
  }

  /**
   * Creates a button markup for adding a notification.
   *
   * @return an InlineKeyboardMarkup containing a notification button
   */
  public InlineKeyboardMarkup createAddNotificationButtonMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("Add Notification"),
      List.of(1),
      List.of(notification_new.name())
    );
  }

  /**
   * Creates a button markup for setting a reminder.
   *
   * @return an InlineKeyboardMarkup containing a set reminder button
   */
  public InlineKeyboardMarkup createSetReminderButtonMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("Set Reminder"),
      List.of(1),
      List.of(notification_main.name())
    );
  }

  private String getStatusText(Object value, String label) {
    return (value != null && !(value instanceof String && ((String) value).isBlank())) ? "✅ " + label : "❌ " + label;
  }
}
