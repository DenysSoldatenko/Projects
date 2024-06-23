package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

  private final UserService userService;

  /**
   * Get a user by their unique identifier.
   *
   * @param id The unique identifier of the user to retrieve.
   * @return A ResponseEntity containing the user information if found (HttpStatus.OK),
   *     or HttpStatus.NO_CONTENT if not found.
   */
  @GetMapping(value = "/{id}")
  public UserDto getUserById(@PathVariable(name = "id") Long id) {
    return userService.getById(id);
  }
}