package com.example.notificationbot.managers.helpers;

import static com.example.notificationbot.data.CallbackData.*;
import static com.example.notificationbot.entities.NotificationStatus.IN_PROGRESS;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Action;
import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.entities.NotificationStatus;
import com.example.notificationbot.keyboards.KeyboardFactory;
import com.example.notificationbot.repositories.NotificationRepository;
import com.example.notificationbot.repositories.UserRepository;
import com.example.notificationbot.utlis.NotificationContainer;
import com.example.notificationbot.utlis.ReplyMarkupEditor;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryNotificationHelper {

  UserRepository userRepository;
  KeyboardFactory keyboardFactory;
  ReplyMarkupEditor replyMarkupEditor;
  NotificationRepository notificationRepository;

  public BotApiMethod<?> sendNotification(CallbackQuery query, String id, TelegramBot bot) throws TelegramApiException {
    var notification = notificationRepository.findById(UUID.fromString(id)).orElseThrow();
    if (notification.getTitle() == null || notification.getTitle().isBlank() || notification.getDuration() == null) {
      return AnswerCallbackQuery.builder()
        .callbackQueryId(query.getId())
        .text("Please fill in the required values: Title and Time")
        .build();
    }
    bot.execute(
        AnswerCallbackQuery.builder()
          .text("The notification will come to you in " + notification.getDuration() + " seconds \uD83D\uDC40")
          .callbackQueryId(query.getId())
          .build()
    );
    notification.setStatus(IN_PROGRESS);
    notificationRepository.save(notification);
    Thread.startVirtualThread(
      new NotificationContainer(
        query.getMessage().getChatId(),
        bot,
        notification,
        notificationRepository
      )
    );
    return EditMessageText.builder()
      .text("✅ Successfully")
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .replyMarkup(
        keyboardFactory.createInlineKeyboard(
          List.of("Go to main"),
          List.of(1),
          List.of(main.name())
        )
      )
      .build();
  }

  public BotApiMethod<?> editPage(CallbackQuery query, String id) {
    return EditMessageText.builder()
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .text("Configure the notification")
      .replyMarkup(replyMarkupEditor.editNotificationReplyMarkup(id))
      .build();
  }

  public BotApiMethod<?> askSeconds(CallbackQuery query, String id) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    user.setAction(Action.SENDING_TIME);
    user.setCurrentNotification(UUID.fromString(id));
    userRepository.save(user);
    return EditMessageText.builder()
      .text("⚡\uFE0F Enter the time after which you want to receive a reminder\nFormat - HH:MM:SS\nFor example - (01:30:00) - one and a half hours")
      .messageId(query.getMessage().getMessageId())
      .chatId(query.getMessage().getChatId())
      .replyMarkup(
        keyboardFactory.createInlineKeyboard(
          List.of("\uD83D\uDD19 Back"),
          List.of(1),
          List.of(notification_back_ + id)
        )
      )
      .build();
  }

  public BotApiMethod<?> askDescription(CallbackQuery query, String id) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    user.setAction(Action.SENDING_DESCRIPTION);
    user.setCurrentNotification(UUID.fromString(id));
    userRepository.save(user);
    return EditMessageText.builder()
      .text("⚡\uFE0F Add or change the description by simply writing in the chat the text you would like to receive")
      .messageId(query.getMessage().getMessageId())
      .chatId(query.getMessage().getChatId())
      .replyMarkup(
        keyboardFactory.createInlineKeyboard(
          List.of("\uD83D\uDD19 Back"),
          List.of(1),
          List.of(notification_back_ + id)
        )
      )
      .build();
  }

  public BotApiMethod<?> askTitle(CallbackQuery query, String id) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    user.setAction(Action.SENDING_TITLE);
    user.setCurrentNotification(UUID.fromString(id));
    userRepository.save(user);
    return EditMessageText.builder()
      .text("⚡\uFE0F Describe a brief title in the next message so that it is immediately clear what I am reminding you of")
      .messageId(query.getMessage().getMessageId())
      .chatId(query.getMessage().getChatId())
      .replyMarkup(
        keyboardFactory.createInlineKeyboard(
          List.of("\uD83D\uDD19 Back"),
          List.of(1),
          List.of(notification_back_ + id)
        )
      )
      .build();
  }

  public BotApiMethod<?> newNotification(CallbackQuery query) {
    var user = userRepository.findByChatId(query.getMessage().getChatId());
    String id = String.valueOf(notificationRepository.save(
        Notification.builder()
          .user(user)
          .status(NotificationStatus.PENDING)
          .build()
    ).getId());
    return EditMessageText.builder()
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .text("Configure the notification")
      .replyMarkup(replyMarkupEditor.editNotificationReplyMarkup(id))
      .build();
  }
}
