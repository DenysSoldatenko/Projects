package com.example.notificationbot.utlis;

import static com.example.notificationbot.data.CallbackData.main;
import static com.example.notificationbot.data.CallbackData.notification_done_;
import static com.example.notificationbot.data.CallbackData.notification_edit_d_;
import static com.example.notificationbot.data.CallbackData.notification_edit_time_;
import static com.example.notificationbot.data.CallbackData.notification_edit_title_;

import com.example.notificationbot.keyboards.KeyboardFactory;
import com.example.notificationbot.repositories.NotificationRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
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
public class ReplyMarkupEditor {

  NotificationRepository notificationRepository;
  KeyboardFactory keyboardFactory;

  /**
   * Edits the reply markup for a notification based on its ID.
   *
   * @param id The UUID of the notification to be edited.
   * @return An InlineKeyboardMarkup object representing the updated keyboard.
   * @throws NoSuchElementException if the notification with the given ID does not exist.
   */
  public InlineKeyboardMarkup editNotificationReplyMarkup(String id) {
    var notification = notificationRepository.findNotificationById(UUID.fromString(id)).orElseThrow();

    List<String> text = List.of(
          getStatusText(notification.getTitle(), "Title"),
          getStatusText(notification.getDuration(), "Time"),
          getStatusText(notification.getDescription(), "Description"),
          "🔙 Main",
          "✅ Done"
    );

    return keyboardFactory.createInlineKeyboard(
      text,
      List.of(2, 1, 2),
      List.of(
        notification_edit_title_.name() + id,
        notification_edit_time_.name() + id,
        notification_edit_d_.name() + id,
        main.name(),
        notification_done_.name() + id
      )
    );
  }

  private String getStatusText(Object value, String label) {
    return (value != null && !(value instanceof String && ((String) value).isBlank())) ? "✅ " + label : "❌ " + label;
  }
}
