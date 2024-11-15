package com.example.militarytrackerbot.services;

import static com.example.militarytrackerbot.factories.MessageFactory.createAnswerCallback;
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
   * @param query The callback query containing the user input.
   * @param input The raw user input, potentially containing a single date or date range.
   * @return A response method for the bot (either data or an error message).
   */
  public BotApiMethod<?> processDateInput(CallbackQuery query, String input) {
    if (input == null || input.trim().isEmpty()) {
      return createAnswerCallback(query, INVALID_SINGLE_DATE_FORMAT_MESSAGE);
    }
    return input.contains(" – ") ? handleDateRange(query, input) : handleSingleDate(query, input);
  }

  private BotApiMethod<?> handleDateRange(CallbackQuery query, String input) {
    String[] dates = input.split(" – ");

    if (dates.length != 2) {
      return createAnswerCallback(query, INVALID_DATE_RANGE_FORMAT_MESSAGE);
    }

    String startDate = dates[0].trim();
    String endDate = dates[1].trim();

    if (isInvalidDateFormat(startDate) || isInvalidDateFormat(endDate) || parse(endDate).isBefore(parse(startDate))) {
      return createAnswerCallback(query, isInvalidDateFormat(startDate)
        ? INVALID_START_DATE_MESSAGE : (isInvalidDateFormat(endDate)
        ? INVALID_END_DATE_MESSAGE : END_DATE_BEFORE_START_DATE_MESSAGE)
      );
    }

    String queryParams = createQueryParamsForPeriod(startDate, endDate);
    return dataFetchService.fetchPaginatedData(query, baseUrl + "?" + queryParams, queryParams, startDate, endDate);
  }

  private BotApiMethod<?> handleSingleDate(CallbackQuery query, String input) {
    try {
      LocalDate date = parse(input.trim(), getDateFormatter());
      return dataFetchService.fetchAndFormatData(query, baseUrl + "/" + date);
    } catch (DateTimeParseException e) {
      log.warn("Invalid single date format: {}", input);
      return createAnswerCallback(query, INVALID_SINGLE_DATE_FORMAT_MESSAGE);
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
}
