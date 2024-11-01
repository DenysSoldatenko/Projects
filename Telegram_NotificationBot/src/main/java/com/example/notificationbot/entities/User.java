package com.example.notificationbot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Represents a user in the system.
 * This class contains the user's details, including their chat ID,
 * first name, registration time, and associated notifications.
 */
@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class User extends AbstractEntity {

  @Column(name = "chat_id", unique = true, nullable = false)
  Long chatId;

  @Column(name = "first_name", nullable = false)
  String firstName;

  @Column(name = "registered_at", nullable = false)
  LocalDateTime registeredAt;

  @OneToMany
  Set<Notification> notifications;
}
