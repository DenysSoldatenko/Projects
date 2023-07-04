package com.example.demo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a student entity.
 */
@Data
@Builder
@Document(collection = "student")
public class Student {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private Gender gender;
  private Address address;
  private List<String> favouriteSubjects;
  private BigDecimal totalSpentInBooks;
  private LocalDateTime created;
}