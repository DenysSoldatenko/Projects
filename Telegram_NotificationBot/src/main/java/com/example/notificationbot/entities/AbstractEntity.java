package com.example.notificationbot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Abstract base class for entities in the application.
 * This class provides a unique identifier for all entities that extend it.
 */
@Entity
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;
}
