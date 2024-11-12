package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.utils.MessageUtils.MILITARY_DATA_FETCH_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.NO_MILITARY_DATA_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.UNEXPECTED_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.PaginationUtils.decrementOffset;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateFrom;
import static com.example.militarytrackerbot.utils.PaginationUtils.getDateTo;
import static com.example.militarytrackerbot.utils.PaginationUtils.incrementOffset;
import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;

import com.example.militarytrackerbot.dtos.MultipleDaysDataDto;
import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import com.example.militarytrackerbot.exceptions.InvalidOffsetException;
import com.example.militarytrackerbot.utils.ResponseFormatterUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Service class that provides methods to fetch and format military data from an external API,
 * handles pagination for data, and returns formatted responses to be used in a Telegram bot.
 * <p>
 * This class interacts with the military API to fetch the latest day and weekly data, processes the
 * data, and handles paginated data requests,
 * using appropriate error handling and response formatting.
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataProvider {

  @Value("${military.base-url}")
  private String baseUrl;

  @Value("${military.latest-url}")
  private String latestUrl;

  private static final DateTimeFormatter DATE_FORMATTER = ofPattern("yyyy-MM-dd");

  private final RestTemplate restTemplate;
  private final ResponseFormatterUtils responseFormatter;

  /**
   * Fetches and formats data for the latest day.
   *
   * @return A formatted string with the latest day data.
   */
  public String getDataForLatestDay() {
    return fetchAndFormatData(latestUrl, responseFormatter::formatForLatestDay);
  }

  /**
   * Fetches and formats data for the last week.
   *
   * @return A Map containing the formatted response and URL parameters.
   */
  public Map<String, String> getDataForWeek() {
    LocalDate today = now();
    String dateFrom = today.minusDays(7).format(DATE_FORMATTER);
    String dateTo = today.format(DATE_FORMATTER);
    String queryParams = "date_from=" + dateFrom + "&date_to=" + dateTo + "&offset=0&limit=2";

    String url = baseUrl + "?" + queryParams;
    return fetchAndFormatPeriodData(url, dateFrom, dateTo, queryParams);
  }

  /**
   * Fetches and formats paginated data based on callback query action.
   *
   * @param query  The Telegram callback query object.
   * @param params The current URL parameters.
   * @return A Map containing the formatted response and updated parameters.
   */
  public Map<String, String> getDataForPagination(CallbackQuery query, String params) {
    try {
      String updatedParams = "PREV".equals(query.getData().split("\\$")[0])
          ? decrementOffset(params)
          : incrementOffset(params);

      String dateFrom = getDateFrom(params);
      String dateTo = getDateTo(params);
      String url = baseUrl + "?" + updatedParams;

      return fetchAndFormatPeriodData(url, dateFrom, dateTo, updatedParams);
    } catch (InvalidOffsetException e) {
      log.error("Invalid offset in pagination parameters: {}", e.getMessage());
      return createErrorResult(NO_MILITARY_DATA_MESSAGE);
    }
  }

  private String fetchAndFormatData(String url, FormatterFunction<SingleDayDataDto> formatter) {
    try {
      SingleDayDataDto response = restTemplate.getForObject(url, SingleDayDataDto.class);
      if (response == null) {
        log.warn("No data found at URL: {}", url);
        return NO_MILITARY_DATA_MESSAGE;
      }
      return formatter.format(response);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("Error fetching data: {}", e.getMessage());
      return MILITARY_DATA_FETCH_ERROR_MESSAGE;
    } catch (Exception e) {
      log.error("Unexpected error occurred: {}", e.getMessage());
      return UNEXPECTED_ERROR_MESSAGE;
    }
  }

  private Map<String, String> fetchAndFormatPeriodData(String url, String dateFrom, String dateTo, String params) {
    try {
      MultipleDaysDataDto response = restTemplate.getForObject(url, MultipleDaysDataDto.class);
      if (response == null || response.getData().getRecords().isEmpty()) {
        log.warn("No data found at URL: {}", url);
        return createErrorResult(NO_MILITARY_DATA_MESSAGE);
      }
      String formattedResponse = responseFormatter.formatForPeriod(response, dateFrom, dateTo);
      return createResultMap(formattedResponse, params);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("Error fetching data: {}", e.getMessage());
      return createErrorResult(MILITARY_DATA_FETCH_ERROR_MESSAGE);
    } catch (Exception e) {
      log.error("Unexpected error occurred: {}", e.getMessage());
      return createErrorResult(UNEXPECTED_ERROR_MESSAGE);
    }
  }

  private Map<String, String> createErrorResult(String message) {
    Map<String, String> result = new HashMap<>();
    result.put("formattedResponse", message);
    return result;
  }

  private Map<String, String> createResultMap(String formattedResponse, String params) {
    Map<String, String> result = new HashMap<>();
    result.put("formattedResponse", formattedResponse);
    result.put("param", params);
    return result;
  }
}
