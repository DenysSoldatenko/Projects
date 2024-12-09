package com.example.notificationbot.repositories;

import com.example.notificationbot.entities.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

  /**
   * Retrieves a {@link User} entity along with its associated notifications
   * based on the provided chat ID.
   *
   * @param chatId The unique identifier of the user (chat ID).
   * @return The {@link User} entity associated with the provided chat ID,
   *     or null if no such user exists.
   */
  @Query("SELECT u FROM User u LEFT JOIN FETCH u.notifications WHERE u.chatId = :chatId")
  User findByChatId(@Param("chatId") Long chatId);

  /**
   * Checks if a user with the given chat ID exists in the database.
   *
   * @param chatId The unique identifier of the user (chat ID).
   * @return {@code true} if a user with the given chat ID exists, {@code false} otherwise.
   */
  boolean existsByChatId(Long chatId);
}