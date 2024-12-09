package com.example.notificationbot.repositories;

import com.example.notificationbot.entities.Notification;
import com.example.notificationbot.entities.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for {@link Notification} entities.
 */
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

  /**
   * Retrieves a {@link Notification} entity by its ID,
   * along with the associated {@link User} and their notifications.
   *
   * @param id The unique identifier of the {@link Notification} to retrieve.
   * @return An {@link Optional} containing the {@link Notification} entity if found,
   *         or empty if not.
   */
  @Query("SELECT n FROM Notification n LEFT JOIN FETCH n.user u LEFT JOIN FETCH u.notifications WHERE n.id = :id")
  Optional<Notification> findNotificationById(@Param("id") UUID id);
}