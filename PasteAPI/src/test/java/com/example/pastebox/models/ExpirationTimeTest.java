package com.example.pastebox.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ExpirationTime class.
 */
public class ExpirationTimeTest {

  @Test
  public void testLabels() {
    assertEquals("10 Minutes", ExpirationTime._10_MINUTES.getLabel());
    assertEquals("1 Hour", ExpirationTime._1_HOUR.getLabel());
    assertEquals("3 Hours", ExpirationTime._3_HOURS.getLabel());
    assertEquals("1 Day", ExpirationTime._1_DAY.getLabel());
    assertEquals("1 Week", ExpirationTime._1_WEEK.getLabel());
    assertEquals("1 Month", ExpirationTime._1_MONTH.getLabel());
  }

  @Test
  public void testEnumValues() {
    assertEquals(ExpirationTime.valueOf("_10_MINUTES"), ExpirationTime._10_MINUTES);
    assertEquals(ExpirationTime.valueOf("_1_HOUR"), ExpirationTime._1_HOUR);
    assertEquals(ExpirationTime.valueOf("_3_HOURS"), ExpirationTime._3_HOURS);
    assertEquals(ExpirationTime.valueOf("_1_DAY"), ExpirationTime._1_DAY);
    assertEquals(ExpirationTime.valueOf("_1_WEEK"), ExpirationTime._1_WEEK);
    assertEquals(ExpirationTime.valueOf("_1_MONTH"), ExpirationTime._1_MONTH);
  }
}
