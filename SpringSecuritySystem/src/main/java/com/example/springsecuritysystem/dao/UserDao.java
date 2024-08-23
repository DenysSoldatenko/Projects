package com.example.springsecuritysystem.dao;

import com.example.springsecuritysystem.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) for handling user-related database interactions.
 * This class communicates with the {@link UserService} to retrieve user details.
 */
@Repository
public class UserDao {

  private final UserService userRepository;

  public UserDao(UserService userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }
}
