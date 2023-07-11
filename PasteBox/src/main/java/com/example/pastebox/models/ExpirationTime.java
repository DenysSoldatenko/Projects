package com.example.pastebox.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpirationTime {
  _10_MINUTES("10 Minutes"),
  _1_HOUR("1 Hour"),
  _3_HOURS("3 Hours"),
  _1_DAY("1 Day"),
  _1_WEEK("1 Week"),
  _1_MONTH("1 Month");

  private final String label;
}
