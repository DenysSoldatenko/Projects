package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.factories.MessageFactory.createAnswerCallback;
import static com.example.militarytrackerbot.factories.MessageFactory.createEditMessageResponse;
import static com.example.militarytrackerbot.utils.MessageUtils.MILITARY_DATA_FETCH_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.NO_MILITARY_DATA_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.UNEXPECTED_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.ResponseFormatterUtils.formatForLatestDay;
import static com.example.militarytrackerbot.utils.ResponseFormatterUtils.formatForPeriod;

import com.example.militarytrackerbot.dtos.MultipleDaysDataDto;
import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import com.example.militarytrackerbot.factories.DataKeyboardFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Service responsible for fetching and processing military data from a remote API.
 * It handles fetching data for both single-day and paginated (multiple days) requests,
 * and formats the data before returning it to the user.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataFetchService {

  RestTemplate restTemplate;
  DataKeyboardFactory dataKeyboardFactory;

  /**
   * Fetches data from the specified URL and formats the response according to the type of data requested.
   *
   * @param query The callback query from the user.
   * @param url The URL to fetch the data from.
   * @param params Any additional parameters to be included in the request.
   * @param dateFrom The start date for fetching data.
   * @param dateTo The end date for fetching data.
   * @param responseType The class type representing the response (either SingleDayDataDto or MultipleDaysDataDto).
   * @param isPaginated Whether the request expects paginated data (multiple days).
   * @param <T> The type of response expected from the request (SingleDayDataDto or MultipleDaysDataDto).
   * @return A BotApiMethod representing the response to be sent to the user.
   */
  public <T> BotApiMethod<?> fetchData(CallbackQuery query, String url, String params, String dateFrom, String dateTo, Class<T> responseType, boolean isPaginated) {
    try {
      T response = restTemplate.getForObject(url, responseType);
      if (response == null) {
        return createAnswerCallback(query, NO_MILITARY_DATA_MESSAGE);
      }
      if (isPaginated && response instanceof MultipleDaysDataDto multipleDaysData) {
        return handlePaginatedResponse(query, multipleDaysData, dateFrom, dateTo, params);
      }
      if (!isPaginated && response instanceof SingleDayDataDto singleDayData) {
        return handleSingleDayResponse(query, singleDayData);
      }
      log.error("Unexpected response type: {}", responseType.getName());
      return createAnswerCallback(query, UNEXPECTED_ERROR_MESSAGE);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("HTTP error while fetching data from URL [{}]: {}", url, e.getMessage());
      return createAnswerCallback(query, MILITARY_DATA_FETCH_ERROR_MESSAGE);
    } catch (Exception e) {
      log.error("Unexpected error fetching data from URL [{}]: {}", url, e.getMessage());
      return createAnswerCallback(query, UNEXPECTED_ERROR_MESSAGE);
    }
  }

  private BotApiMethod<?> handlePaginatedResponse(CallbackQuery query, MultipleDaysDataDto response, String dateFrom, String dateTo, String params) {
    if (response.getData().getRecords().isEmpty()) {
      return createAnswerCallback(query, NO_MILITARY_DATA_MESSAGE);
    }
    return createEditMessageResponse(query, formatForPeriod(response, dateFrom, dateTo), dataKeyboardFactory.createPaginationButtonsMarkup(params));
  }

  private BotApiMethod<?> handleSingleDayResponse(CallbackQuery query, SingleDayDataDto response) {
    return createEditMessageResponse(query, formatForLatestDay(response), dataKeyboardFactory.createBackButtonMarkup());
  }

  /**
   * Fetches paginated military data from the API and returns the formatted response.
   *
   * @param query The callback query from the user.
   * @param url The URL to fetch the data from.
   * @param params The parameters for pagination.
   * @param dateFrom The start date for the data range.
   * @param dateTo The end date for the data range.
   * @return A BotApiMethod representing the response to be sent to the user.
   */
  public BotApiMethod<?> fetchPaginatedData(CallbackQuery query, String url, String params, String dateFrom, String dateTo) {
    return fetchData(query, url, params, dateFrom, dateTo, MultipleDaysDataDto.class, true);
  }

  /**
   * Fetches and formats data for a single day from the API.
   *
   * @param query The callback query from the user.
   * @param url The URL to fetch the data from.
   * @return A BotApiMethod representing the response to be sent to the user.
   */
  public BotApiMethod<?> fetchAndFormatData(CallbackQuery query, String url) {
    return fetchData(query, url, "", "", "", SingleDayDataDto.class, false);
  }
}
