package com.example.pastebox.utils;

import com.example.pastebox.models.ExpirationTime;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

/**
 * Utility class for generating expiration times based on the specified duration.
 */
@UtilityClass
public class ExpirationTimeGenerator {

  /**
   * Generates an expiration time based on the specified duration and the date of creation.
   *
   * @param dateOfCreation The date and time when the paste was created.
   * @param expirationTime The duration for which the paste should be valid.
   * @return The calculated expiration date and time.
   */
  public LocalDateTime generateExpirationTime(LocalDateTime dateOfCreation, ExpirationTime expirationTime) {
    return switch (expirationTime) {
      case _10_MINUTES -> dateOfCreation.plusMinutes(10);
      case _1_HOUR -> dateOfCreation.plusHours(1);
      case _3_HOURS -> dateOfCreation.plusHours(3);
      case _1_DAY -> dateOfCreation.plusDays(1);
      case _1_WEEK -> dateOfCreation.plusWeeks(1);
      case _1_MONTH -> dateOfCreation.plusMonths(1);
    };
  }
}

