package com.example.notificationbot.entities;

/**
 * Enum representing the status of a notification.
 */
public enum NotificationStatus {
  PENDING,    // Notification is waiting to be processed
  IN_PROGRESS, // Notification is currently being processed
  COMPLETED   // Notification has been processed successfully
}
