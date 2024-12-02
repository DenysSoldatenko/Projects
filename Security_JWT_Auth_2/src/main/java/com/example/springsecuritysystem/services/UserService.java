package com.example.springsecuritysystem.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service interface for managing user-related operations in the Spring Security system.
 * This interface provides a method for retrieving a user by their email address.
 */
public interface UserService {

  /**
   * Retrieves a user by their email address.
   *
   * @param email The email address of the user to find.
   * @return The {@link UserDetails} representing the user with the specified email.
   *         Returns {@code null} or throws an exception if the user is not found.
   */
  UserDetails findUserByEmail(String email);
}
