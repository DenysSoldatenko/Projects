package com.example.demo.initializers;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static java.util.stream.IntStream.range;

import com.example.demo.models.Address;
import com.example.demo.models.Gender;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Component responsible for initializing the database with sample data.
 */
@Slf4j
@Component
@AllArgsConstructor
public class StudentInitializer {

  private static final int STUDENT_COUNT = 11;

  private final Faker faker = new Faker();
  private final StudentRepository repository;

  /**
   * Initializes the database with sample student records.
   */
  public String initDatabase() {
    range(1, STUDENT_COUNT)
      .mapToObj(this::createRandomStudent)
      .forEach(repository::insert);

    return "Sample database initialized with 10 random records.";
  }

  /**
   * Creates a random student using Faker.
   */
  private Student createRandomStudent(int id) {
    Address address = new Address(
      faker.address().country(),
      faker.address().city(),
      faker.address().zipCode()
    );

    return Student.builder()
      .id(String.valueOf(id))
      .firstName(faker.name().firstName())
      .lastName(faker.name().lastName())
      .email(faker.internet().emailAddress())
      .gender(faker.options().option(Gender.class))
      .address(address)
      .favouriteSubjects(of(faker.educator().course(), faker.educator().course()))
      .totalSpentInBooks(valueOf(faker.number().randomDouble(2, 1, 1000)))
      .created(now())
      .build();
  }
}
