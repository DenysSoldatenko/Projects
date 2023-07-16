package com.example.pastebox.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a paste, which can be stored in a database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paste {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String content;
  private LocalDateTime creationTime;
  private LocalDateTime expirationTime;

  @Enumerated(EnumType.STRING)
  private ExpirationTime expirationDuration;

  @Enumerated(EnumType.STRING)
  private PublicStatus publicStatus;
  private String shortLink;
}
