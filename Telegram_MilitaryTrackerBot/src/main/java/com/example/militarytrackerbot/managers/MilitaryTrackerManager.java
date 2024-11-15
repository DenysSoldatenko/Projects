package com.example.militarytrackerbot.managers;

import com.example.militarytrackerbot.services.DataProviderService;
import com.example.militarytrackerbot.services.DateRangeParserService;
import java.util.Arrays;
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

  MainManager mainManager;
  DataProviderService dataProviderService;
  DateRangeParserService dateInputParserService;

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
      case "MAIN" -> mainManager.showMainMenu(query);
      case "OPTIONS", "BACK" -> mainManager.showAvailableCommands(query);
      case "DAY" -> dataProviderService.provideLatestDayData(query);
      case "WEEK" -> dataProviderService.provideWeeklyData(query);
      case "MONTH" -> dataProviderService.provideMonthlyData(query);
      case "PERIOD" -> dataProviderService.promptForDateSelection(query);
      default -> throw new IllegalStateException("Unexpected value: " + words[0]);
    };
  }

  private BotApiMethod<?> handleTwoWordsQuery(String[] words, CallbackQuery query) {
    Arrays.stream(words).forEach(System.out::println);
    return switch (words[0]) {
      case "EMPTY" -> dataProviderService.promptForDateSelection(query);
      case "INPUT" -> dataProviderService.promptForDateSelection(query, words[1]);
      case "SUBMIT" -> dateInputParserService.processDateInput(query, words[1].substring(5));
      case "PREV", "NEXT" -> dataProviderService.handlePagination(query, words[1]);
      default -> throw new IllegalStateException("Unexpected value: " + words[0]);
    };
  }
}
