package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastMonth;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFromLastWeek;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getToday;
import static com.example.militarytrackerbot.utils.PaginationUtils.adjustOffset;
import static com.example.militarytrackerbot.utils.PaginationUtils.createQueryParamsForPeriod;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateFrom;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateTo;

import com.example.militarytrackerbot.utils.DataFetchUtils;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Service class responsible for providing formatted data for different time periods.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataProvider {

  @Value("${military.base-url}")
  private String baseUrl;

  @Value("${military.latest-url}")
  private String latestUrl;

  private final DataFetchUtils dataFetcher;

  /**
   * Fetches and formats the data for the latest day.
   *
   * @return A string containing the formatted data for the latest day.
   */
  public Map<String, String> getDataForLatestDay() {
    return dataFetcher.fetchAndFormatData(latestUrl);
  }

  /**
   * Fetches and formats the data for the past week.
   *
   * @return A Map containing the formatted data for the past week, including query parameters.
   */
  public Map<String, String> getDataForWeek() {
    String dateFrom = getDateFromLastWeek();
    String dateTo = getToday();
    String queryParams = createQueryParamsForPeriod(dateFrom, dateTo);
    String url = baseUrl + "?" + queryParams;
    return dataFetcher.fetchPaginatedData(url, queryParams, dateFrom, dateTo);
  }

  /**
   * Fetches and formats the data for the past month.
   *
   * @return A Map containing the formatted data for the past month, including query parameters.
   */
  public Map<String, String> getDataForMonth() {
    String dateFrom = getDateFromLastMonth();
    String dateTo = getToday();
    String queryParams = createQueryParamsForPeriod(dateFrom, dateTo);
    String url = baseUrl + "?" + queryParams;
    return dataFetcher.fetchPaginatedData(url, queryParams, dateFrom, dateTo);
  }

  /**
   * Fetches paginated data based on user interaction (pagination).
   *
   * @param query The CallbackQuery object containing user interaction data.
   * @param params The query parameters containing pagination information.
   * @return A Map containing the formatted paginated data, including updated query parameters.
   */
  public Map<String, String> getDataForPagination(CallbackQuery query, String params) {
    String dateFrom = getDateFrom(params);
    String dateTo = getDateTo(params);
    String updatedParams = adjustOffset(query.getData(), params);
    String url = baseUrl + "?" + updatedParams;
    return dataFetcher.fetchPaginatedData(url, updatedParams, dateFrom, dateTo);
  }
}
