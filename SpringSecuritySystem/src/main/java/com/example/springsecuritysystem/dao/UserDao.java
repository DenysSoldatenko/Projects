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

  public UserDetails findUserByEmail(String email) {
    return userService.findUserByEmail(email);
  }
}
