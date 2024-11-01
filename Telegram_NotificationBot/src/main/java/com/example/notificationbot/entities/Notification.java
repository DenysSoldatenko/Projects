package com.example.notificationbot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Represents a notification entity in the system.
 * This class contains the details of the notification, including its title, description,
 * status, duration, and the user associated with it.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "notifications")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Notification extends AbstractEntity {

  @Column(name = "title")
  Long title;

  @Column(name = "description")
  String description;

  @Column(name = "status")
  @Enumerated(value = EnumType.STRING)
  NotificationStatus status;

  @Column(name = "duration")
  Long duration;

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;
}
