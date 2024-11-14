package com.example.militarytrackerbot.utils;

import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

/**
 * Utility class for working with date ranges.
 * Provides methods to retrieve the date, all in the "yyyy-MM-dd" format.
 */
@UtilityClass
public class DateRangeUtils {

  private static final DateTimeFormatter DATE_FORMATTER = ofPattern("yyyy-MM-dd");

  public static String getToday() {
    return now().format(DATE_FORMATTER);
  }

  public static String getDateFromLastWeek() {
    return now().minusDays(7).format(DATE_FORMATTER);
  }

  public static String getDateFromLastMonth() {
    return now().minusMonths(1).format(DATE_FORMATTER);
  }
}
