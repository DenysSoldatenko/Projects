package com.example.militarytrackerbot.dtos;

import lombok.Data;

/**
 * DTO representing the response structure for a single day's military data.
 *
 * <p>This class holds the response message and a single {@link RecordDto} object, which contains
 * the statistics and increase for one specific day.
 */
@Data
public class SingleDayDataDto {

  private String message;
  private RecordDto data;
}
