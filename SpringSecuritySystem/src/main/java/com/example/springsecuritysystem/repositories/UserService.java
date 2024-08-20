package com.example.springsecuritysystem.repositories;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service interface for managing user-related operations in the Spring Security system.
 * Provides a method to find a user by their email address.
 */
public interface UserService {
  UserDetails findUserByEmail(String email);
}
