package com.example.weatherbot.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for representing weather conditions (description, code, icon).
 */
@Getter
@Setter
public class Weather {

  private int code;
  private String icon;
  private String description;
}
