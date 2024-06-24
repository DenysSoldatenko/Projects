package com.example.jwtsecuritysystem.controllers;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.AuthenticationRequestDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.services.AuthenticationService;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user authentication operations.
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

  private final UserService userService;
  private final AuthenticationService authenticationService;

  /**
   * Endpoint for user authentication and token generation.
   *
   * @param requestDto The authentication request containing username and password.
   * @return A ResponseEntity containing the username and authentication token.
   */
  @PostMapping("/login")
  public Map<Object, Object> login(@RequestBody AuthenticationRequestDto requestDto) {
    return authenticationService.login(requestDto);
  }

  @PostMapping(value = "/createAdmin")
  public AdminDto createAdmin(@RequestBody User user) {
    return userService.createAdmin(user);
  }

  @PostMapping(value = "/createUser")
  public UserDto createUser(@RequestBody User user) {
    return userService.createUser(user);
  }
}