package com.example.notificationbot.managers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.listeners.CommandListener;
import com.example.notificationbot.listeners.QueryListener;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager extends AbstractManager implements QueryListener, CommandListener {

  @Override
  public BotApiMethod<?> processCommand(Message message, TelegramBot telegramBot) {
    return greetings(message.getChatId());
  }

  @Override
  public BotApiMethod<?> processQuery(CallbackQuery query, TelegramBot telegramBot) {
    return null;
  }

  @Override
  public BotApiMethod<?> showMainMenu(Message message, TelegramBot telegramBot) {
    return null;
  }

  @Override
  public BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot telegramBot) {
    return null;
  }

  public BotApiMethod<?> greetings(Long chatId) {
    return SendMessage.builder()
      .chatId(chatId)
      .text("Hello World!")
      .build();
  }
}
