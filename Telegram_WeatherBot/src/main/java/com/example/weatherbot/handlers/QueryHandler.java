package com.example.weatherbot.handlers;

import com.example.weatherbot.configurations.TelegramBot;
import com.example.weatherbot.managers.MainManager;
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

  @Override
  public BotApiMethod<?> handle(BotApiObject object, TelegramBot bot) {
    var query = (CallbackQuery) object;
    String[] words = query.getData().split("_");

    if (words.length == 0) {
      throw new UnsupportedOperationException("Empty query data");
    }

    return switch (words[0]) {
      case "weather" -> null;
      case "main" -> mainManager.processQuery(query);
      default -> throw new UnsupportedOperationException("Unsupported query type: " + words[0]);
    };
  }
}
