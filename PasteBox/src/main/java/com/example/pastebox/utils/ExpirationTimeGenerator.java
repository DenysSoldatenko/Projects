package com.example.pastebox.utils;

import com.example.pastebox.models.ExpirationTime;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ExpirationTimeGenerator {

  public LocalDateTime generate(LocalDateTime dateOfCreation, ExpirationTime expirationTime) {
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

