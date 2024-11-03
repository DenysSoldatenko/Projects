package com.example.notificationbot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Represents a user in the system.
 * This class contains the user's details, including their chat ID,
 * first name, registration time, and associated notifications.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @Column(name = "chat_id", unique = true, nullable = false)
  Long chatId;

  @Column(name = "first_name", nullable = false)
  String firstName;

  @Enumerated(EnumType.STRING)
  Action action;

  @Column(name = "registered_at", nullable = false)
  LocalDateTime registeredAt;

  @OneToMany
  Set<Notification> notifications;

  @Column(name = "current_notification_id")
  UUID currentNotification;
}
