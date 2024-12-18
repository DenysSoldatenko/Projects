package com.example.notificationbot.managers;

import static com.example.notificationbot.factories.NotificationMessageFactory.createEditMessageResponse;
import static com.example.notificationbot.factories.NotificationMessageFactory.createMessageResponse;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.factories.NotificationMarkupFactory;
import com.example.notificationbot.listeners.CommandListener;
import com.example.notificationbot.listeners.QueryListener;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager implements QueryListener, CommandListener {

  static String WELCOME_MESSAGE = "Welcome, dear friend!";

  NotificationMarkupFactory notificationMarkupFactory;

  @Override
  public BotApiMethod<?> processCommand(Message message, TelegramBot telegramBot) {
    return createMessageResponse(
      message,
      WELCOME_MESSAGE,
      notificationMarkupFactory.createSetReminderButtonMarkup()
    );
  }

  @Override
  public BotApiMethod<?> processQuery(CallbackQuery query, String[] words, TelegramBot telegramBot) {
    return createEditMessageResponse(
      query,
      WELCOME_MESSAGE,
      notificationMarkupFactory.createSetReminderButtonMarkup()
    );
  }
}
