package com.example.militarytrackerbot.managers;

import com.example.militarytrackerbot.processors.QueryProcessor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * The {@link MilitaryTrackerManager} class manages the logic for processing user queries
 * in response to button clicks from the Telegram bot. It uses factories to generate the appropriate
 * keyboard and message responses based on user interaction.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MilitaryTrackerManager {

  QueryProcessor queryProcessor;

  /**
   * Processes the callback query based on the number of words in the query data.
   *
   * @param query The callback query containing user interaction data.
   * @param words The query data split into an array of strings.
   * @return The response to be sent back to the user.
   * @throws IllegalStateException If the number of words is not 1 or 2.
   */
  public BotApiMethod<?> processQuery(CallbackQuery query, String[] words) {
    return switch (words.length) {
      case 1 -> handleOneWordQuery(words, query);
      case 2 -> handleTwoWordsQuery(words, query);
      default -> throw new IllegalStateException("Unexpected number of words: " + words.length);
    };
  }

  private BotApiMethod<?> handleOneWordQuery(String[] words, CallbackQuery query) {
    return switch (words[0]) {
      case "MAIN" -> queryProcessor.handleMainMenu(query);
      case "OPTIONS", "BACK" -> queryProcessor.handleViewOptions(query);
      case "DAY" -> queryProcessor.handleDayStats(query);
      case "WEEK" -> queryProcessor.handleWeekStats(query);
      case "MONTH" -> queryProcessor.handleMonthStats(query);
      case "PERIOD" -> queryProcessor.handleSetDate(query);
      default -> throw new IllegalStateException("Unexpected value: " + words[0]);
    };
  }

  private BotApiMethod<?> handleTwoWordsQuery(String[] words, CallbackQuery query) {
    return switch (words[0]) {
      case "EMPTY" -> queryProcessor.handleSetDate(query);
      case "INPUT" -> queryProcessor.handleSetDate(query, words[1]);
      case "SUBMIT" -> null;
      case "PREV", "NEXT" -> queryProcessor.handlePagination(query, words[1]);
      default -> throw new IllegalStateException("Unexpected value: " + words[0]);
    };
  }
}
