package com.example.springsecuritysystem.dao;

import com.example.springsecuritysystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Data Access Object (DAO) for handling user-related database interactions.
 * This class communicates with the {@link UserService} to retrieve user details.
 */
@Service
@RequiredArgsConstructor
public class UserDao {

  private final UserService userService;

  /**
   * Finds a user by their email address.
   *
   * @param email The email address of the user to search for.
   * @return A {@link UserDetails} object representing the user,
   *     or {@code null} if the user is not found.
   */
  public UserDetails findUserByEmail(String email) {
    return userService.findUserByEmail(email);
  }
}
