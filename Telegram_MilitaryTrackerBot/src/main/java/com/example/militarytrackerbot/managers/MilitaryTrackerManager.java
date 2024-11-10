package com.example.militarytrackerbot.managers;

import static com.example.militarytrackerbot.utils.MessageUtils.AVAILABLE_COMMANDS_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.WELCOME_MESSAGE_TEMPLATE;

import com.example.militarytrackerbot.data.CallbackData;
import com.example.militarytrackerbot.factories.MilitaryDataKeyboardFactory;
import com.example.militarytrackerbot.factories.NotificationMessageFactory;
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

  MilitaryDataKeyboardFactory militaryDataKeyboardFactory;
  NotificationMessageFactory notificationMessageFactory;

  /**
   * Processes the callback query from the user based on the provided word.
   * The word represents an action determined by a button click in the bot's interface.
   *
   * @param query The callback query object containing the user's interaction.
   * @param word The action word which corresponds to a specific user request.
   * @return A {@link BotApiMethod} that sends a response to the user.
   */
  public BotApiMethod<?> processQuery(CallbackQuery query, String word) {
    return switch (CallbackData.valueOf(word)) {
      case MAIN -> handleMainMenu(query);
      case OPTIONS -> handleViewOptions(query);
      case DAY -> null;
      case WEEK -> null;
      case MONTH -> null;
      case PERIOD -> null;
      case BACK -> null;
    };
  }

  private BotApiMethod<?> handleMainMenu(CallbackQuery query) {
    return notificationMessageFactory.createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      militaryDataKeyboardFactory.createOptionsMarkup()
    );
  }

  private BotApiMethod<?> handleViewOptions(CallbackQuery query) {
    return notificationMessageFactory.createEditMessageResponse(
      query,
      AVAILABLE_COMMANDS_MESSAGE,
      militaryDataKeyboardFactory.createMainOptionsMarkup()
    );
  }

}
