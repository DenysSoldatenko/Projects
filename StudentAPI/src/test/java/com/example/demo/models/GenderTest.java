package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Gender class.
 */
public class GenderTest {

  @Test
  public void shouldGetMaleGender() {
    assertEquals(Gender.MALE, Gender.valueOf("MALE"));
  }

  @Test
  public void shouldGetFemaleGender() {
    assertEquals(Gender.FEMALE, Gender.valueOf("FEMALE"));
  }

  @Test
  public void shouldGetAllEnumValues() {
    Gender[] genders = Gender.values();
    assertEquals(2, genders.length);
    assertEquals(Gender.MALE, genders[0]);
    assertEquals(Gender.FEMALE, genders[1]);
  }
}
