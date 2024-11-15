package com.example.militarytrackerbot.utils;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

/**
 * A utility class for handling pagination-related operations, such as incrementing or decrementing
 * the offset in a query string, and extracting date ranges from query parameters.
 */
@UtilityClass
public class PaginationUtils {

  /**
   * Creates query parameters for a given date range.
   *
   * @param dateFrom The start date of the period.
   * @param dateTo The end date of the period.
   * @return A string representing the query parameters,
   *     including the date range and pagination details.
   */
  public static String createQueryParamsForPeriod(String dateFrom, String dateTo) {
    return String.format("date_from=%s&date_to=%s&offset=0&limit=2", dateFrom, dateTo);
  }

  /**
   * Extracts the "date_from" parameter from a query string.
   *
   * @param params The query string containing the parameters.
   * @return The value of the "date_from" parameter.
   */
  public static String getDateFrom(String params) {
    return parseQueryParams(params).get("date_from");
  }

  /**
   * Extracts the "date_to" parameter from a query string.
   *
   * @param params The query string containing the parameters.
   * @return The value of the "date_to" parameter.
   */
  public static String getDateTo(String params) {
    return parseQueryParams(params).get("date_to");
  }

  /**
   * Adjusts the "offset" parameter based on the user action ("PREV" or "NEXT").
   *
   * @param action The user action, which is either "PREV" or "NEXT".
   * @param params The current query string containing the parameters.
   * @return The updated query string with the adjusted "offset".
   */
  public static String adjustOffset(String action, String params) {
    Map<String, String> paramMap = parseQueryParams(params);
    int offset = parseInt(paramMap.get("offset"));
    if ("PREV".equals(action.substring(0, 4)) && offset > 0) {
      offset--;
    } else if ("NEXT".equals(action.substring(0, 4))) {
      offset++;
    }
    paramMap.put("offset", String.valueOf(offset));
    return reconstructQueryString(paramMap);
  }

  private static Map<String, String> parseQueryParams(String params) {
    return Stream.of(params.split("&"))
      .map(pair -> pair.split("="))
      .filter(keyValue -> keyValue.length == 2)
      .collect(toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
  }

  private static String reconstructQueryString(Map<String, String> paramMap) {
    return paramMap.entrySet().stream()
      .map(entry -> entry.getKey() + "=" + entry.getValue())
      .collect(joining("&"));
  }
}
