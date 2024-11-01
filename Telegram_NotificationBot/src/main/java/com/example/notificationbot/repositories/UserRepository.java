package com.example.notificationbot.repositories;

import com.example.notificationbot.entities.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

}