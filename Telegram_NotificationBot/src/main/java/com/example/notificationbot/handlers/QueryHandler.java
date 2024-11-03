package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.managers.MainManager;
import com.example.notificationbot.managers.NotificationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Handler for processing callback queries received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryHandler implements BaseHandler {

  MainManager mainManager;
  NotificationManager notificationManager;

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot bot) throws TelegramApiException {
    var query = (CallbackQuery) object;
    String[] words = query.getData().split("_");

    if (words.length == 0) {
      throw new UnsupportedOperationException("Empty query data");
    }

    return switch (words[0]) {
      case "notification" -> notificationManager.processQuery(query, words, bot);
      case "main" -> mainManager.processQuery(query, words, bot);
      default -> throw new UnsupportedOperationException("Unsupported query type: " + words[0]);
    };
  }
}
