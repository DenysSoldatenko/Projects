package com.example.militarytrackerbot.dtos;

import java.util.List;
import lombok.Data;

/**
 * DTO representing the 'data' field, which contains a list of records and paging info.
 */
@Data
public class DataDto {
  private List<RecordDto> records;
}