package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastMonth;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastWeek;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getToday;
import static com.example.militarytrackerbot.utils.PaginationUtils.createQueryParamsForPeriod;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Service for providing military data based on user requests, including fetching data
 * for the latest day, weekly, monthly, and handling pagination for custom date ranges.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageProviderService {

  @Value("${military.base-url}")
  String baseUrl;

  @Value("${military.latest-url}")
  String latestUrl;

  final DataFetchService dataFetchService;

  /**
   * Fetches and returns the latest day's statistics.
   *
   * @return BotApiMethod containing the formatted latest data.
   */
  public BotApiMethod<?> provideLatestDayData(Message message) {
    return dataFetchService.fetchAndFormatData(message, latestUrl);
  }

  /**
   * Fetches and returns data for the past week with pagination.
   *
   * @return BotApiMethod containing formatted weekly data.
   */
  public BotApiMethod<?> provideWeeklyData(Message message) {
    return fetchAndPaginateData(message, getDateFromLastWeek(), getToday());
  }

  /**
   * Fetches and returns data for the past month with pagination.
   *
   * @return BotApiMethod containing formatted monthly data.
   */
  public BotApiMethod<?> provideMonthlyData(Message message) {
    return fetchAndPaginateData(message, getDateFromLastMonth(), getToday());
  }

  /**
   * Helper method to fetch and paginate data based on date range.
   *
   * @param message  The Message object for user interaction.
   * @param dateFrom  The start date for data fetching.
   * @param dateTo    The end date for data fetching.
   * @param params    Query parameters for pagination (optional).
   * @return BotApiMethod containing formatted and paginated data.
   */
  private BotApiMethod<?> fetchAndPaginateData(Message message, String dateFrom, String dateTo, String... params) {
    String queryParams = params.length > 0 ? params[0] : createQueryParamsForPeriod(dateFrom, dateTo);
    String url = baseUrl + "?" + queryParams;
    return dataFetchService.fetchPaginatedData(message, url, queryParams, dateFrom, dateTo);
  }
}
