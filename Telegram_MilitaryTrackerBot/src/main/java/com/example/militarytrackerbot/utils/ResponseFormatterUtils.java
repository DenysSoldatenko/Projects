package com.example.militarytrackerbot.utils;

import static com.example.militarytrackerbot.utils.MessageUtils.STATS_DAY_STATS_TEMPLATE;
import static com.example.militarytrackerbot.utils.MessageUtils.STATS_DISPLAY_MESSAGE_LATEST_DAY;
import static com.example.militarytrackerbot.utils.MessageUtils.STATS_DISPLAY_MESSAGE_PERIOD;

import com.example.militarytrackerbot.dtos.MultipleDaysDataDto;
import com.example.militarytrackerbot.dtos.RecordDto;
import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import java.text.DecimalFormat;
import lombok.experimental.UtilityClass;

/**
 * This class is responsible for formatting the response data from the military tracker
 * into a readable message for display.
 */
@UtilityClass
public class ResponseFormatterUtils {

  /**
   * Formats the response data for the latest day into a readable message.
   *
   * @param response The RecordDto containing the military statistics.
   * @return A formatted string message to display the data.
   */
  public static String formatForLatestDay(SingleDayDataDto response) {
    DecimalFormat df = new DecimalFormat("#,###");

    return String.format(
      STATS_DISPLAY_MESSAGE_LATEST_DAY,
      response.getData().getDay(),
      response.getData().getDate(),
      df.format(response.getData().getStats().getPersonnelUnits()),
      df.format(response.getData().getStats().getTanks()),
      df.format(response.getData().getStats().getArtillerySystems()),
      df.format(response.getData().getStats().getPlanes()),
      df.format(response.getData().getStats().getHelicopters()),
      df.format(response.getData().getStats().getVehiclesFuelTanks()),
      df.format(response.getData().getStats().getWarshipsCutters()),
      df.format(response.getData().getStats().getCruiseMissiles()),
      df.format(response.getData().getStats().getSpecialMilitaryEquip()),
      df.format(response.getData().getStats().getSubmarines()),
      df.format(response.getData().getIncrease().getPersonnelUnits()),
      df.format(response.getData().getIncrease().getTanks()),
      df.format(response.getData().getIncrease().getArtillerySystems()),
      df.format(response.getData().getIncrease().getPlanes()),
      df.format(response.getData().getIncrease().getHelicopters()),
      df.format(response.getData().getIncrease().getVehiclesFuelTanks()),
      df.format(response.getData().getIncrease().getWarshipsCutters()),
      df.format(response.getData().getIncrease().getCruiseMissiles()),
      df.format(response.getData().getIncrease().getSpecialMilitaryEquip()),
      df.format(response.getData().getIncrease().getSubmarines())
    );
  }

  /**
   * Formats the response data for a given period (e.g., date range) into a readable message.
   *
   * @param response  The MultipleDaysDataDto containing the military statistics for the period.
   * @param startDate The start date of the period.
   * @param endDate   The end date of the period.
   * @return A formatted string message to display the data for the period.
   */
  public static String formatForPeriod(MultipleDaysDataDto response, String startDate, String endDate) {
    DecimalFormat df = new DecimalFormat("#,###");
    StringBuilder periodStats = new StringBuilder();

    for (RecordDto record : response.getData().getRecords()) {
      periodStats.append(String.format(
          STATS_DAY_STATS_TEMPLATE,
          record.getDate(),
          df.format(record.getStats().getPersonnelUnits()),
          df.format(record.getStats().getTanks()),
          df.format(record.getStats().getArtillerySystems()),
          df.format(record.getStats().getPlanes()),
          df.format(record.getStats().getHelicopters()),
          df.format(record.getStats().getVehiclesFuelTanks()),
          df.format(record.getStats().getWarshipsCutters()),
          df.format(record.getStats().getCruiseMissiles()),
          df.format(record.getStats().getSpecialMilitaryEquip()),
          df.format(record.getStats().getSubmarines()),
          df.format(record.getIncrease().getPersonnelUnits()),
          df.format(record.getIncrease().getTanks()),
          df.format(record.getIncrease().getArtillerySystems()),
          df.format(record.getIncrease().getPlanes()),
          df.format(record.getIncrease().getHelicopters()),
          df.format(record.getIncrease().getVehiclesFuelTanks()),
          df.format(record.getIncrease().getWarshipsCutters()),
          df.format(record.getIncrease().getCruiseMissiles()),
          df.format(record.getIncrease().getSpecialMilitaryEquip()),
          df.format(record.getIncrease().getSubmarines())
      ));
    }

    return String.format(STATS_DISPLAY_MESSAGE_PERIOD, startDate, endDate, periodStats);
  }
}
