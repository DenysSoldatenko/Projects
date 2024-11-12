package com.example.militarytrackerbot.utils;

import static com.example.militarytrackerbot.utils.MessageUtils.OFFSET_ERROR_MESSAGE;

import com.example.militarytrackerbot.exceptions.InvalidOffsetException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

/**
 * A utility class for handling pagination-related operations, such as incrementing or decrementing
 * the offset in a query string, and extracting date ranges from query parameters.
 */
@UtilityClass
public class PaginationUtils {

  public static String incrementOffset(String params) {
    return updateOffset(params, 1);
  }

  public static String decrementOffset(String params) {
    return updateOffset(params, -1);
  }

  public static String getDateFrom(String params) {
    return parseQueryParams(params).get("date_from");
  }

  public static String getDateTo(String params) {
    return parseQueryParams(params).get("date_to");
  }

  private static String updateOffset(String params, int increment) {
    Map<String, String> paramMap = parseQueryParams(params);
    int offset = Integer.parseInt(paramMap.getOrDefault("offset", "0")) + increment;

    if (offset < 0) {
      throw new InvalidOffsetException(OFFSET_ERROR_MESSAGE);
    }

    paramMap.put("offset", String.valueOf(offset));
    return reconstructQueryString(paramMap);
  }

  private static Map<String, String> parseQueryParams(String params) {
    return Stream.of(params.split("&"))
      .map(pair -> pair.split("="))
      .filter(keyValue -> keyValue.length == 2)
      .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
  }

  private static String reconstructQueryString(Map<String, String> paramMap) {
    return paramMap.entrySet().stream()
      .map(entry -> entry.getKey() + "=" + entry.getValue())
      .collect(Collectors.joining("&"));
  }
}
