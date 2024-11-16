package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.factories.MessageFactory.createEditMessageResponse;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastMonth;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastWeek;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getToday;
import static com.example.militarytrackerbot.utils.MessageUtils.ENTER_PERIOD_OR_DATE_MESSAGE;
import static com.example.militarytrackerbot.utils.PaginationUtils.adjustOffset;
import static com.example.militarytrackerbot.utils.PaginationUtils.createQueryParamsForPeriod;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateFrom;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateTo;

import com.example.militarytrackerbot.factories.DataKeyboardFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Service for providing military data based on user requests, including fetching data
 * for the latest day, weekly, monthly, and handling pagination for custom date ranges.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueryProviderService {

  @Value("${military.base-url}")
  String baseUrl;

  @Value("${military.latest-url}")
  String latestUrl;

  final DataFetchService dataFetchService;
  final DataKeyboardFactory dataKeyboardFactory;

  /**
   * Fetches and returns the latest day's statistics.
   *
   * @return BotApiMethod containing the formatted latest data.
   */
  public BotApiMethod<?> provideLatestDayData(CallbackQuery query) {
    return dataFetchService.fetchAndFormatData(query, latestUrl);
  }

  /**
   * Fetches and returns data for the past week with pagination.
   *
   * @return BotApiMethod containing formatted weekly data.
   */
  public BotApiMethod<?> provideWeeklyData(CallbackQuery query) {
    return fetchAndPaginateData(query, getDateFromLastWeek(), getToday());
  }

  /**
   * Fetches and returns data for the past month with pagination.
   *
   * @return BotApiMethod containing formatted monthly data.
   */
  public BotApiMethod<?> provideMonthlyData(CallbackQuery query) {
    return fetchAndPaginateData(query, getDateFromLastMonth(), getToday());
  }

  /**
   * Initiates date selection by prompting the user.
   *
   * @return BotApiMethod prompting for date or period input.
   */
  public BotApiMethod<?> promptForDateSelection(CallbackQuery query) {
    return createEditMessageResponse(
      query,
      ENTER_PERIOD_OR_DATE_MESSAGE,
      dataKeyboardFactory.createPeriodEnteringKeyboardMarkup("")
    );
  }

  /**
   * Prompts the user to select a date or period for military data,
   * and includes the user-typed input on the interactive keyboard.
   *
   * @param query The callback query from the user.
   * @param input A user-typed input to be displayed on the keyboard.
   * @return A BotApiMethod prompting the user for date or period input,
   *         with the user-typed input included in the interactive keyboard.
   */
  public BotApiMethod<?> promptForDateSelection(CallbackQuery query, String input) {
    return createEditMessageResponse(
      query,
      ENTER_PERIOD_OR_DATE_MESSAGE,
      dataKeyboardFactory.createPeriodEnteringKeyboardMarkup(input)
    );
  }

  /**
   * Fetches paginated data based on user interaction.
   *
   * @param query  The CallbackQuery object with user interaction data.
   * @param params Query parameters with pagination info.
   * @return BotApiMethod containing paginated data.
   */
  public BotApiMethod<?> handlePagination(CallbackQuery query, String params) {
    String dateFrom = getDateFrom(params);
    String dateTo = getDateTo(params);
    String updatedParams = adjustOffset(query.getData(), params);
    return fetchAndPaginateData(query, dateFrom, dateTo, updatedParams);
  }

  /**
   * Helper method to fetch and paginate data based on date range.
   *
   * @param query     The CallbackQuery object for user interaction.
   * @param dateFrom  The start date for data fetching.
   * @param dateTo    The end date for data fetching.
   * @param params    Query parameters for pagination (optional).
   * @return BotApiMethod containing formatted and paginated data.
   */
  private BotApiMethod<?> fetchAndPaginateData(CallbackQuery query, String dateFrom, String dateTo, String... params) {
    String queryParams = params.length > 0 ? params[0] : createQueryParamsForPeriod(dateFrom, dateTo);
    String url = baseUrl + "?" + queryParams;
    return dataFetchService.fetchPaginatedData(query, url, queryParams, dateFrom, dateTo);
  }
}
