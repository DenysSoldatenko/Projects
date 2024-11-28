package com.example.securitysystem.services;

import com.example.securitysystem.entities.User;

/**
 * Service interface for managing users in the security system.
 *
 * <p>This interface provides methods for user sign-up,
 * saving tokens, and enabling user accounts.</p>
 */
public interface UserService {

  /**
   * Signs up a new user and registers them in the system.
   *
   * @param appUser The user entity containing the user's registration details.
   * @return A confirmation message or error message regarding the sign-up process.
   */
  String signUp(User appUser);

  /**
   * Saves the token associated with the user, typically for email confirmation or password reset.
   *
   * @param user The user entity with the token to be saved.
   * @return A confirmation message regarding the token saving.
   */
  String saveToken(User user);

  /**
   * Enables a user account by setting the user's {@code enabled} field to {@code true}.
   *
   * @param email The email of the user to be enabled.
   */
  void enableUser(String email);
}
