package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.factories.MessageFactory.createAnswerCallback;
import static com.example.militarytrackerbot.factories.MessageFactory.createMessageResponse;
import static com.example.militarytrackerbot.utils.DateRangeUtils.getDateFormatter;
import static com.example.militarytrackerbot.utils.MessageUtils.END_DATE_BEFORE_START_DATE_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.INVALID_DATE_RANGE_FORMAT_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.INVALID_END_DATE_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.INVALID_SINGLE_DATE_FORMAT_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.INVALID_START_DATE_MESSAGE;
import static com.example.militarytrackerbot.utils.PaginationUtils.createQueryParamsForPeriod;
import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Service for processing and validating user date inputs,
 * including single dates and date ranges.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DateRangeParserService {

  @Value("${military.base-url}")
  String baseUrl;

  final DataFetchService dataFetchService;

  /**
   * Processes the date input, determining whether it's a single date or a date range.
   *
   * @param request The request object, which could be either a CallbackQuery or Message.
   * @param input The raw user input, potentially containing a single date or date range.
   * @return A response method for the bot (either data or an error message).
   */
  public BotApiMethod<?> processDateInput(Object request, String input) {
    if (input == null || input.trim().isEmpty()) {
      return createErrorResponse(request, INVALID_SINGLE_DATE_FORMAT_MESSAGE);
    }
    return input.contains(" – ") ? handleDateRange(request, input) : handleSingleDate(request, input);
  }

  private BotApiMethod<?> handleDateRange(Object request, String input) {
    String[] dates = input.split(" – ");

    if (dates.length != 2) {
      return createErrorResponse(request, INVALID_DATE_RANGE_FORMAT_MESSAGE);
    }

    String startDate = dates[0].trim();
    String endDate = dates[1].trim();

    if (isInvalidDateFormat(startDate) || isInvalidDateFormat(endDate) || parse(endDate).isBefore(parse(startDate))) {
      String errorMessage = isInvalidDateFormat(startDate)
          ? INVALID_START_DATE_MESSAGE : (isInvalidDateFormat(endDate)
          ? INVALID_END_DATE_MESSAGE : END_DATE_BEFORE_START_DATE_MESSAGE);
      return createErrorResponse(request, errorMessage);
    }

    String queryParams = createQueryParamsForPeriod(startDate, endDate);
    return dataFetchService.fetchPaginatedData(request, baseUrl + "?" + queryParams, queryParams, startDate, endDate);
  }

  private BotApiMethod<?> handleSingleDate(Object request, String input) {
    try {
      LocalDate date = parse(input.trim(), getDateFormatter());
      return dataFetchService.fetchAndFormatData(request, baseUrl + "/" + date);
    } catch (DateTimeParseException e) {
      log.warn("Invalid single date format: {}", input);
      return createErrorResponse(request, INVALID_SINGLE_DATE_FORMAT_MESSAGE);
    }
  }

  private boolean isInvalidDateFormat(String date) {
    try {
      parse(date, getDateFormatter());
      return false;
    } catch (DateTimeParseException e) {
      log.warn("Invalid date format: {}", date);
      return true;
    }
  }

  private BotApiMethod<?> createErrorResponse(Object request, String message) {
    if (request instanceof CallbackQuery callbackQuery) {
      return createAnswerCallback(callbackQuery, message);
    } else if (request instanceof Message messageObj) {
      return createMessageResponse(messageObj, message);
    }
    throw new IllegalArgumentException("Unsupported request type: " + request.getClass().getName());
  }
}
