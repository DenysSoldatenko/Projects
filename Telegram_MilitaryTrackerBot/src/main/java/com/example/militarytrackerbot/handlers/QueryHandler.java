package com.example.militarytrackerbot.handlers;

import com.example.militarytrackerbot.managers.MilitaryTrackerManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Handler for processing callback queries received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryHandler {

  MilitaryTrackerManager militaryTrackerManager;

  /**
   * Processes a callback query and delegates the task to the MilitaryTrackerManager.
   *
   * @param query The callback query received from the bot.
   * @return A BotApiMethod response to be sent back to the user.
   */
  public BotApiMethod<?> handle(CallbackQuery query) {
    String[] words = query.getData().split("\\$");

    if (words.length == 0) {
      throw new UnsupportedOperationException("Empty query data");
    }

    return militaryTrackerManager.processQuery(query, words);
  }
}
