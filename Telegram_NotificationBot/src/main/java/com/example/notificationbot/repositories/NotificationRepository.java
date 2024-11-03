package com.example.notificationbot.repositories;

import com.example.notificationbot.entities.Notification;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for {@link Notification} entities.
 */
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

  @Query("SELECT n FROM Notification n LEFT JOIN FETCH n.user u LEFT JOIN FETCH u.notifications WHERE n.id = :id")
  Optional<Notification> findNotificationById(@Param("id") UUID id);
}