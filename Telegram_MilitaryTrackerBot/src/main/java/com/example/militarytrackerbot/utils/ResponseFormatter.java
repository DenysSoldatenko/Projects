package com.example.militarytrackerbot.utils;

import static com.example.militarytrackerbot.utils.MessageUtils.STATS_DISPLAY_MESSAGE_LATEST_DAY;

import com.example.militarytrackerbot.dtos.SingleDayDataDto;
import java.text.DecimalFormat;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for formatting the response data from the military tracker
 * into a readable message for display.
 */
@Component
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class ResponseFormatter {

  /**
   * Formats the response data for the latest day into a readable message.
   *
   * @param response The RecordDto containing the military statistics.
   * @return A formatted string message to display the data.
   */
  public String formatForLatestDay(SingleDayDataDto response) {
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
}
