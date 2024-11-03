package com.example.notificationbot.managers.query;

import static com.example.notificationbot.data.CallbackData.notification_new;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.keyboards.KeyboardFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Manages callback queries related to notifications in the Telegram bot.
 * This class handles user interactions through callback queries and updates
 * the chat interface accordingly.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryManagerImpl implements QueryManager {

  KeyboardFactory keyboardFactory;

  @Override
  public BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot bot) {
    return EditMessageText.builder()
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .text("Please add a notification below ↓")
      .replyMarkup(
        keyboardFactory.createInlineKeyboard(
          List.of("Add Notification"),
          List.of(1),
          List.of(notification_new.name())
        )
      )
      .build();
  }
}
