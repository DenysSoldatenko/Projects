package com.example.notificationbot.managers;

import static com.example.notificationbot.data.CallbackData.notification_main;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.keyboards.KeyboardFactory;
import com.example.notificationbot.listeners.CommandListener;
import com.example.notificationbot.listeners.QueryListener;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager implements QueryListener, CommandListener {

  KeyboardFactory keyboardFactory;

  @Override
  public BotApiMethod<?> processCommand(Message message, TelegramBot telegramBot) {
    return SendMessage.builder()
      .chatId(message.getChatId())
      .text("Welcome, dear friend!")
      .replyMarkup(createInlineKeyboard())
      .build();
  }

  @Override
  public BotApiMethod<?> processQuery(CallbackQuery query, String[] words, TelegramBot telegramBot) {
    return EditMessageText.builder()
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .text("Welcome, dear friend!")
      .replyMarkup(createInlineKeyboard())
      .build();
  }

  private InlineKeyboardMarkup createInlineKeyboard() {
    return keyboardFactory.createInlineKeyboard(
      List.of("Set Reminder"),
      List.of(1),
      List.of(notification_main.name())
    );
  }
}
