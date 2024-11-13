package com.example.notificationbot.managers.query;

import static com.example.notificationbot.factories.NotificationMessageFactory.createEditMessageResponse;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.factories.NotificationMarkupFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

  NotificationMarkupFactory notificationMarkupFactory;

  @Override
  public BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot bot) {
    return createEditMessageResponse(
      query,
      "Please add a notification below ↓",
      notificationMarkupFactory.createAddNotificationButtonMarkup()
    );
  }
}
