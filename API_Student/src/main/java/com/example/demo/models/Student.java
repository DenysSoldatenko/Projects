package com.example.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a student entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
@Schema(description = "Represents a student entity")
public class Student {

  @Id
  @Schema(
      description = "Unique identifier of the student",
      example = "11"
  )
  private String id;

  @Schema(
      description = "The first name of the student",
      example = "John"
  )
  private String firstName;

  @Schema(
      description = "The last name of the student",
      example = "Doe"
  )
  private String lastName;

  @Indexed(unique = true)
  @Schema(
      description = "The email address of the student",
      example = "john.doe@example.com"
  )
  private String email;

  @Schema(
      description = "The gender of the student",
      example = "MALE"
  )
  private Gender gender;

  @Schema(
      description = "The address of the student",
      example = "{\"country\": \"United States\", \"city\": \"New York\", \"postCode\": \"10001\"}"
  )
  private Address address;

  @Schema(
      description = "List of favorite subjects of the student",
      example = "[\"Maths\", \"Arts\", \"Science\"]"
  )
  private List<String> favouriteSubjects;

  @Schema(
      description = "Total amount spent on books by the student",
      example = "150.75"
  )
  private BigDecimal totalSpentInBooks;

  @Schema(
      description = "Timestamp of when the student was created",
      example = "2024-10-04T20:29:01.203"
  )
  private LocalDateTime created;
}