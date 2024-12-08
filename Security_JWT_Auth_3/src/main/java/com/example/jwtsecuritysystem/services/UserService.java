package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.User;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

  /**
   * Creates a new regular user and returns a {@link UserDto} representing the created user.
   *
   * <p>This method is used to register a new user with the regular user role.
   *
   * @param user The {@link User} entity containing the user details.
   * @return The {@link UserDto} representing the created user.
   */
  UserDto createUser(User user);

  /**
   * Retrieves a {@link User} entity by their username.
   *
   * <p>This method fetches the user details by the provided username. It returns the corresponding
   * {@link User} entity if found.
   *
   * @param username The username of the user to retrieve.
   * @return The {@link User} entity with the specified username.
   */
  User getByUsername(String username);

  /**
   * Retrieves a {@link UserDto} by user ID.
   *
   * <p>This method fetches the user details and returns a {@link UserDto} that includes information
   * about the user.
   *
   * @param id The unique ID of the user to retrieve.
   * @return The {@link UserDto} containing the user details.
   */
  UserDto getById(Long id);

  /**
   * Retrieves an {@link AdminDto} by admin ID.
   *
   * <p>This method fetches the details of an admin user
   * by their unique ID and returns an {@link AdminDto}.
   *
   * @param id The unique ID of the admin to retrieve.
   * @return The {@link AdminDto} containing the admin details.
   */
  AdminDto getAdminById(Long id);

  /**
   * Removes a user by their ID.
   *
   * <p>This method deletes the user associated with the specified ID from the system.
   *
   * @param id The unique ID of the user to be removed.
   */
  void removeUser(Long id);

  /**
   * Creates a new admin user and returns an {@link AdminDto} representing the created admin.
   *
   * <p>This method is used to register a new user with admin privileges.
   *
   * @param user The {@link User} entity containing the admin user details.
   * @return The {@link AdminDto} representing the created admin.
   */
  AdminDto createAdmin(User user);
}