package com.example.militarytrackerbot.utils;

import static com.example.militarytrackerbot.utils.MessageUtils.MILITARY_DATA_FETCH_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.NO_MILITARY_DATA_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.UNEXPECTED_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.ResponseFormatterUtils.formatForLatestDay;
import static com.example.militarytrackerbot.utils.ResponseFormatterUtils.formatForPeriod;
import static com.example.militarytrackerbot.utils.ResponseUtils.createResponseMapWithFormattedMessage;
import static com.example.militarytrackerbot.utils.ResponseUtils.createResponseMapWithParams;

import com.example.militarytrackerbot.dtos.MultipleDaysDataDto;
import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import java.util.Map;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Utility class for fetching and formatting data from external APIs.
 *
 * <p>This class provides methods to fetch both paginated and non-paginated data from external APIs.
 * It uses {@link RestTemplate} to send HTTP requests and handles the data retrieval process.
 * The responses are then formatted using {@link ResponseFormatterUtils}
 * based on the type of data (single-day or multiple-days).
 */
@Slf4j
@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataFetchUtils {

  RestTemplate restTemplate = new RestTemplate();

  /**
   * Fetches data from the given URL and formats it based on the response type and pagination flag.
   *
   * @param url          The URL to fetch data from.
   * @param params       Query parameters for paginated data.
   * @param dateFrom     Start date for data range.
   * @param dateTo       End date for data range.
   * @param responseType The expected response class type.
   * @param isPaginated  Indicates if the response is paginated.
   * @return A map with formatted data or an error message.
   */
  public <T> Map<String, String> fetchData(String url, String params,
                                           String dateFrom, String dateTo,
                                           Class<T> responseType, boolean isPaginated) {
    try {
      T response = restTemplate.getForObject(url, responseType);

      if (response == null) {
        return createResponseMapWithFormattedMessage(NO_MILITARY_DATA_MESSAGE);
      }

      if (isPaginated && response instanceof MultipleDaysDataDto multipleDaysData) {
        return multipleDaysData.getData().getRecords().isEmpty()
          ? createResponseMapWithFormattedMessage(NO_MILITARY_DATA_MESSAGE)
          : createResponseMapWithParams(formatForPeriod(multipleDaysData, dateFrom, dateTo), params);
      } else if (!isPaginated && response instanceof SingleDayDataDto singleDayData) {
        return createResponseMapWithParams(formatForLatestDay(singleDayData), params);
      } else {
        log.error("Unexpected response type: {}", responseType.getName());
        return createResponseMapWithFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
      }

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("HTTP error while fetching data from URL [{}]: {}", url, e.getMessage());
      return createResponseMapWithFormattedMessage(MILITARY_DATA_FETCH_ERROR_MESSAGE);
    } catch (Exception e) {
      log.error("Unexpected error fetching data from URL [{}]: {}", url, e.getMessage());
      return createResponseMapWithFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
    }
  }

  /**
   * Fetches paginated data from the provided URL and formats it.
   *
   * @param url        The URL to fetch the data from.
   * @param params     The query parameters for paginated data.
   * @param dateFrom   Start date of the data range.
   * @param dateTo     End date of the data range.
   * @return A map with the formatted data or an error message.
   */
  public Map<String, String> fetchPaginatedData(String url, String params, String dateFrom, String dateTo) {
    return fetchData(url, params, dateFrom, dateTo, MultipleDaysDataDto.class, true);
  }

  /**
   * Fetches and formats data for the latest day.
   *
   * @param url The URL to fetch the latest day data from.
   * @return A map containing the formatted response or an error message.
   */
  public Map<String, String> fetchAndFormatData(String url) {
    return fetchData(url, "", "", "", SingleDayDataDto.class, false);
  }
}
