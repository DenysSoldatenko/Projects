package com.example.militarytrackerbot.utils;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

/**
 * Utility class for working with date ranges.
 * Provides methods to retrieve the date, all in the "yyyy-MM-dd" format.
 */
@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DateRangeUtils {

  static DateTimeFormatter DATE_FORMATTER = ofPattern("yyyy-MM-dd");

  public static String getToday() {
    return now().format(DATE_FORMATTER);
  }

  public static String getDateFromLastWeek() {
    return now().minusDays(7).format(DATE_FORMATTER);
  }

  public static String getDateFromLastMonth() {
    return now().minusMonths(1).format(DATE_FORMATTER);
  }

  public static DateTimeFormatter getDateFormatter() {
    return DATE_FORMATTER;
  }
}
