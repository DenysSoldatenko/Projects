package com.example.pastebox.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ExpirationTime class.
 */
class ExpirationTimeTest {

  @Test
  void testLabels() {
    assertEquals("10 Minutes", ExpirationTime._10_MINUTES.getLabel());
    assertEquals("1 Hour", ExpirationTime._1_HOUR.getLabel());
    assertEquals("3 Hours", ExpirationTime._3_HOURS.getLabel());
    assertEquals("1 Day", ExpirationTime._1_DAY.getLabel());
    assertEquals("1 Week", ExpirationTime._1_WEEK.getLabel());
    assertEquals("1 Month", ExpirationTime._1_MONTH.getLabel());
  }

  @Test
  void testEnumValues() {
    assertEquals(ExpirationTime._10_MINUTES, ExpirationTime.valueOf("_10_MINUTES"));
    assertEquals(ExpirationTime._1_HOUR, ExpirationTime.valueOf("_1_HOUR"));
    assertEquals(ExpirationTime._3_HOURS, ExpirationTime.valueOf("_3_HOURS"));
    assertEquals(ExpirationTime._1_DAY, ExpirationTime.valueOf("_1_DAY"));
    assertEquals(ExpirationTime._1_WEEK, ExpirationTime.valueOf("_1_WEEK"));
    assertEquals(ExpirationTime._1_MONTH, ExpirationTime.valueOf("_1_MONTH"));
  }
}
