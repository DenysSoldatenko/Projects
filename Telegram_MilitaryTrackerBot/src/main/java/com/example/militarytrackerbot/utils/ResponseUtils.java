package com.example.militarytrackerbot.utils;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

/**
 * Utility class for creating response maps with formatted messages and query parameters.
 */
@UtilityClass
public class ResponseUtils {

  /**
   * Creates a response map with a formatted message.
   *
   * @param message The message to include in the formatted response.
   * @return A map containing the formatted response message.
   */
  public static Map<String, String> createResponseMapWithFormattedMessage(String message) {
    Map<String, String> result = new HashMap<>();
    result.put("formattedResponse", message);
    return result;
  }

  /**
   * Creates a response map containing a formatted message and associated query parameters.
   *
   * @param formattedResponse The formatted response message.
   * @param params The query parameters to associate with the response.
   * @return A map containing both the formatted response and query parameters.
   */
  public static Map<String, String> createResponseMapWithParams(String formattedResponse, String params) {
    Map<String, String> result = createResponseMapWithFormattedMessage(formattedResponse);
    result.put("param", params);
    return result;
  }
}
