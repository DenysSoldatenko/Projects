package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.factories.MessageFactory.createAnswerCallback;
import static com.example.militarytrackerbot.factories.MessageFactory.createEditMessageResponse;
import static com.example.militarytrackerbot.factories.MessageFactory.createMessageResponse;
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
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Service for fetching and processing military data from external sources.
 * It handles both paginated and non-paginated responses, formats the data accordingly,
 * and prepares appropriate responses for both regular messages and callback queries.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataFetchService {

  RestTemplate restTemplate;
  DataKeyboardFactory dataKeyboardFactory;

  /**
   * Fetches data from the specified URL and processes it
   * based on the response type and pagination status.
   *
   * @param request    The request object (message or callback query) that triggered the data fetch.
   * @param url        The URL to fetch data from.
   * @param params     Query parameters for pagination (if applicable).
   * @param dateFrom   The start date for the data range (if applicable).
   * @param dateTo     The end date for the data range (if applicable).
   * @param responseType The type of response expected (e.g., SingleDayDataDto or MultipleDaysDataDto).
   * @param isPaginated A flag indicating if the response is paginated (true for paginated data, false for single data).
   * @param <T>        The type of the response data (either SingleDayDataDto or MultipleDaysDataDto).
   * @return A {@link BotApiMethod} containing the fetched and processed data, or an error message if something goes wrong.
   */
  public <T> BotApiMethod<?> fetchData(Object request, String url, String params, String dateFrom, String dateTo, Class<T> responseType, boolean isPaginated) {
    try {
      T response = restTemplate.getForObject(url, responseType);
      if (response == null) {
        return createResponse(request, NO_MILITARY_DATA_MESSAGE);
      }
      if (isPaginated && response instanceof MultipleDaysDataDto multipleDaysData) {
        return handlePaginatedResponse(request, multipleDaysData, dateFrom, dateTo, params);
      }
      if (!isPaginated && response instanceof SingleDayDataDto singleDayData) {
        return handleSingleDayResponse(request, singleDayData);
      }
      log.error("Unexpected response type: {}", responseType.getName());
      return createResponse(request, UNEXPECTED_ERROR_MESSAGE);
    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("HTTP error while fetching data from URL [{}]: {}", url, e.getMessage());
      return createResponse(request, MILITARY_DATA_FETCH_ERROR_MESSAGE);
    } catch (Exception e) {
      log.error("Unexpected error fetching data from URL [{}]: {}", url, e.getMessage());
      return createResponse(request, UNEXPECTED_ERROR_MESSAGE);
    }
  }

  private BotApiMethod<?> handlePaginatedResponse(Object request, MultipleDaysDataDto response, String dateFrom, String dateTo, String params) {
    if (response.getData().getRecords().isEmpty()) {
      return createResponse(request, NO_MILITARY_DATA_MESSAGE);
    }
    String formattedMessage = formatForPeriod(response, dateFrom, dateTo);
    return isCallbackQuery(request)
      ? createEditMessageResponse((CallbackQuery) request, formattedMessage, dataKeyboardFactory.createPaginationButtonsMarkup(params))
      : createMessageResponse((Message) request, formattedMessage, dataKeyboardFactory.createPaginationButtonsMarkup(params));
  }

  private BotApiMethod<?> handleSingleDayResponse(Object request, SingleDayDataDto response) {
    String formattedMessage = formatForLatestDay(response);
    return isCallbackQuery(request)
      ? createEditMessageResponse((CallbackQuery) request, formattedMessage, dataKeyboardFactory.createBackButtonMarkup())
      : createMessageResponse((Message) request, formattedMessage);
  }

  private BotApiMethod<?> createResponse(Object request, String message) {
    return isCallbackQuery(request) ? createAnswerCallback((CallbackQuery) request, message) : createMessageResponse((Message) request, message);
  }

  /**
   * Fetches paginated data from the specified URL with the given parameters and date range.
   *
   * @param request  The request object (either a message or callback query).
   * @param url      The URL to fetch data from.
   * @param params   The query parameters for pagination.
   * @param dateFrom The start date for the data range.
   * @param dateTo   The end date for the data range.
   * @return A {@link BotApiMethod} containing the paginated data.
   */
  public BotApiMethod<?> fetchPaginatedData(Object request, String url, String params, String dateFrom, String dateTo) {
    return fetchData(request, url, params, dateFrom, dateTo, MultipleDaysDataDto.class, true);
  }

  /**
   * Fetches and formats data from the specified URL without pagination.
   *
   * @param request The request object (either a message or callback query).
   * @param url     The URL to fetch data from.
   * @return A {@link BotApiMethod} containing the formatted data.
   */
  public BotApiMethod<?> fetchAndFormatData(Object request, String url) {
    return fetchData(request, url, "", "", "", SingleDayDataDto.class, false);
  }

  private boolean isCallbackQuery(Object request) {
    return request instanceof CallbackQuery;
  }
}
