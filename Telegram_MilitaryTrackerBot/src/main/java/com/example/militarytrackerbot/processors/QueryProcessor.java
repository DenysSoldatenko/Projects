package com.example.militarytrackerbot.processors;

import static com.example.militarytrackerbot.utils.MessageUtils.AVAILABLE_COMMANDS_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.WELCOME_MESSAGE_TEMPLATE;

import com.example.militarytrackerbot.factories.DataKeyboardFactory;
import com.example.militarytrackerbot.factories.MessageFactory;
import com.example.militarytrackerbot.services.DataProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * The {@code QueryProcessor} class handles different types of callback queries from users
 * and processes them accordingly, generating the appropriate message responses.
 *
 * <p>It interacts with the {@link DataProvider} to fetch the relevant data,
 * the {@link MessageFactory} to create the message content,
 * and the {@link DataKeyboardFactory} to generate the appropriate
 * keyboard markup for user interaction.
 * </p>
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryProcessor {

  DataProvider dataProvider;
  MessageFactory messageFactory;
  DataKeyboardFactory dataKeyboardFactory;

  /**
   * Handles the main menu callback query.
   *
   * <p>This method is triggered when a user interacts
   * with the main menu button in the Telegram bot.
   * It responds with a welcome message and displays the main options keyboard.
   * </p>
   *
   * @param query The callback query containing user interaction data.
   * @return A BotApiMethod object representing the edit message response.
   */
  public BotApiMethod<?> handleMainMenu(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      dataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Handles the view options callback query.
   *
   * <p>This method is triggered when a user selects to view available commands or options.
   * It responds with a list of available commands and displays the main options keyboard.
   * </p>
   *
   * @param query The callback query containing user interaction data.
   * @return A BotApiMethod object representing the edit message response.
   */
  public BotApiMethod<?> handleViewOptions(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      AVAILABLE_COMMANDS_MESSAGE,
      dataKeyboardFactory.createMainOptionsMarkup()
    );
  }

  /**
   * Handles the day stats callback query.
   *
   * <p>This method is triggered when a user selects to view statistics for the latest day.
   * It fetches the data from the {@link DataProvider}, formats it, and responds with the data,
   * along with a back button to return to the previous menu.
   * </p>
   *
   * @param query The callback query containing user interaction data.
   * @return A BotApiMethod object representing the edit message response with day statistics.
   */
  public BotApiMethod<?> handleDayStats(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      dataProvider.getDataForLatestDay().get("formattedResponse"),
      dataKeyboardFactory.createBackButtonMarkup()
    );
  }

  /**
   * Handles the week stats callback query.
   *
   * <p>This method is triggered when a user selects to view statistics for the past week.
   * It fetches the week data from the {@link DataProvider} and responds with the formatted data
   * along with pagination buttons if the data is paginated.
   * </p>
   *
   * @param query The callback query containing user interaction data.
   * @return A BotApiMethod object representing the edit message response with week statistics.
   */
  public BotApiMethod<?> handleWeekStats(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      dataProvider.getDataForWeek().get("formattedResponse"),
      dataKeyboardFactory.createPaginationButtonsMarkup(dataProvider.getDataForWeek().get("param"))
    );
  }

  /**
   * Handles the month stats callback query.
   *
   * <p>This method is triggered when a user selects to view statistics for the past month.
   * It fetches the month data from the {@link DataProvider} and responds with the formatted data
   * along with pagination buttons if the data is paginated.</p>
   *
   * @param query The callback query containing user interaction data.
   * @return A BotApiMethod object representing the edit message response
   *     with month statistics and pagination.
   */
  public BotApiMethod<?> handleMonthStats(CallbackQuery query) {
    return messageFactory.createEditMessageResponse(
      query,
      dataProvider.getDataForMonth().get("formattedResponse"),
      dataKeyboardFactory.createPaginationButtonsMarkup(dataProvider.getDataForMonth().get("param"))
    );
  }

  /**
   * Handles the pagination callback query.
   *
   * <p>This method is triggered when a user interacts
   * with pagination buttons (e.g., "Next" or "Previous").
   * It fetches the paginated data from the {@link DataProvider}, formats the response, and displays
   * the updated message along with pagination controls.
   * </p>
   *
   * @param query The callback query containing user interaction data.
   * @param parameters The query parameters
   *                   that include pagination information (e.g., offset and limit).
   * @return A BotApiMethod object representing the edit message response with paginated data.
   */
  public BotApiMethod<?> handlePagination(CallbackQuery query, String parameters) {
    return messageFactory.createEditMessageResponse(
      query,
      dataProvider.getDataForPagination(query, parameters).get("formattedResponse"),
      dataKeyboardFactory.createPaginationButtonsMarkup(dataProvider.getDataForPagination(query, parameters).get("param"))
    );
  }
}
