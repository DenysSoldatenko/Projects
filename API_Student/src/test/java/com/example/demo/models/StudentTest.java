package com.example.demo.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Student class.
 */
class StudentTest {

  @Test
  void shouldCreateStudent() {
    Address address = new Address();
    address.setCountry("USA");
    address.setCity("New York");
    address.setPostCode("10001");

    LocalDateTime timeOfCreation = LocalDateTime.now();

    Student student = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .email("john.doe@example.com")
        .gender(Gender.MALE)
        .address(address)
        .favouriteSubjects(List.of("Math", "History"))
        .totalSpentInBooks(BigDecimal.valueOf(100.0))
        .created(timeOfCreation)
        .build();

    assertNotNull(student);
    assertEquals("123", student.getId());
    assertEquals("John", student.getFirstName());
    assertEquals("Doe", student.getLastName());
    assertEquals("john.doe@example.com", student.getEmail());
    assertEquals(Gender.MALE, student.getGender());
    assertEquals("USA", student.getAddress().getCountry());
    assertEquals("New York", student.getAddress().getCity());
    assertEquals("10001", student.getAddress().getPostCode());
    assertEquals(List.of("Math", "History"), student.getFavouriteSubjects());
    assertEquals(BigDecimal.valueOf(100.0), student.getTotalSpentInBooks());
    assertEquals(timeOfCreation, student.getCreated());
  }

  @Test
  void shouldCompareStudentsForEquality() {
    Address address = new Address();
    address.setCountry("USA");
    address.setCity("New York");
    address.setPostCode("10001");

    LocalDateTime timeOfCreation = LocalDateTime.now();

    Student student1 = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .email("john.doe@example.com")
        .gender(Gender.MALE)
        .address(address)
        .favouriteSubjects(List.of("Math", "History"))
        .totalSpentInBooks(BigDecimal.valueOf(100.0))
        .created(timeOfCreation)
        .build();

    Student student2 = Student.builder()
        .id("123")
        .firstName("John")
        .lastName("Doe")
        .email("john.doe@example.com")
        .gender(Gender.MALE)
        .address(address)
        .favouriteSubjects(List.of("Math", "History"))
        .totalSpentInBooks(BigDecimal.valueOf(100.0))
        .created(timeOfCreation)
        .build();

    Student student3 = Student.builder()
        .id("124")
        .firstName("Jane")
        .lastName("Doe")
        .email("john.doe@example.com")
        .gender(Gender.MALE)
        .address(address)
        .favouriteSubjects(List.of("Math", "History"))
        .totalSpentInBooks(BigDecimal.valueOf(100.0))
        .created(timeOfCreation)
        .build();

    assertThat(student1)
        .isEqualTo(student2)
        .isNotEqualTo(student3);
  }
}
