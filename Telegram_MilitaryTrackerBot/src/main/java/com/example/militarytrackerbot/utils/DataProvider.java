package com.example.militarytrackerbot.utils;

import static com.example.militarytrackerbot.utils.MessageUtils.MILITARY_DATA_FETCH_ERROR_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.NO_MILITARY_DATA_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.UNEXPECTED_ERROR_MESSAGE;

import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Service class responsible for fetching military data from an external API and
 * formatting it for display.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataProvider {

  @Value("${military.base-url}")
  private String baseUrl;

  @Value("${military.latest-url}")
  private String latestUrl;

  private final RestTemplate restTemplate;
  private final ResponseFormatter responseFormatter;

  /**
   * Fetches the data for the latest day and formats it.
   *
   * @return A formatted string with the latest day data.
   */
  public String getDataForLatestDay() {
    log.info("Fetching latest day data from URL: {}", latestUrl);

    try {
      SingleDayDataDto responseDto = restTemplate.getForObject(latestUrl, SingleDayDataDto.class);
      System.out.println(responseDto);
      if (responseDto == null) {
        log.warn("No data found for the latest day at URL: {}", latestUrl);
        return NO_MILITARY_DATA_MESSAGE;
      }

      String formattedResponse = responseFormatter.formatForLatestDay(responseDto);
      log.info("Successfully fetched and formatted the data for the latest day.");
      return formattedResponse;

    } catch (HttpClientErrorException | HttpServerErrorException e) {
      log.error("Error fetching data from the military API: {}", e.getMessage());
      return MILITARY_DATA_FETCH_ERROR_MESSAGE;
    } catch (Exception e) {
      log.error("Unexpected error occurred: {}", e.getMessage());
      return UNEXPECTED_ERROR_MESSAGE;
    }
  }
}
