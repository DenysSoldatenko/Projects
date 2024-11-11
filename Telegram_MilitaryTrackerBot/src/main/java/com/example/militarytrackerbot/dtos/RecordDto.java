package com.example.militarytrackerbot.dtos;

import lombok.Data;

/**
 * DTO representing an individual record in the 'records' array.
 */
@Data
public class RecordDto {
  private String date;
  private int day;
  private StatsDto stats;
  private StatsDto increase;
}