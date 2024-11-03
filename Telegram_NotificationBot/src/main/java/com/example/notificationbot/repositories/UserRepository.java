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

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.notifications WHERE u.chatId = :chatId")
  User findByChatId(@Param("chatId") Long chatId);

  boolean existsByChatId(Long chatId);
}