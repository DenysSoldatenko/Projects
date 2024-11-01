package com.example.notificationbot.repositories;

import com.example.notificationbot.entities.Notification;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Notification} entities.
 */
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

}