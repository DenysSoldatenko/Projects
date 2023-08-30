package com.example.pastebox.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.pastebox.models.ExpirationTime;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the ExpirationTimeGenerator class.
 */
public class ExpirationTimeGeneratorTest {

  @Test
  public void shouldGenerateExpirationTime() {
    LocalDateTime dateOfCreation = LocalDateTime.now();

    assertEquals(dateOfCreation.plusMinutes(10),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._10_MINUTES));

    assertEquals(dateOfCreation.plusHours(1),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._1_HOUR));

    assertEquals(dateOfCreation.plusHours(3),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._3_HOURS));

    assertEquals(dateOfCreation.plusDays(1),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._1_DAY));

    assertEquals(dateOfCreation.plusWeeks(1),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._1_WEEK));

    assertEquals(dateOfCreation.plusMonths(1),
        ExpirationTimeGenerator.generate(dateOfCreation,
        ExpirationTime._1_MONTH));
  }
}
