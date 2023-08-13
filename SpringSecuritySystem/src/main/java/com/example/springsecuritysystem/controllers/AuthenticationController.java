package com.example.springsecuritysystem.controllers;

import com.example.springsecuritysystem.configurations.JwtUtil;
import com.example.springsecuritysystem.dao.UserDao;
import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication-related requests.
 */
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final UserDao userDao;
  private final JwtUtil jwtUtil;

  /**
   * Authenticates a user
   * and generates a JWT token upon successful authentication.
   *
   * @param request The authentication request containing email and password.
   * @return ResponseEntity with a JWT token if authentication is successful,
   *     or an error response.
   */
  @PostMapping("/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    UserDetails user = userDao.findUserByEmail(request.email());

    if (user != null) {
      return ResponseEntity.ok(jwtUtil.generateToken(user));
    }
    return ResponseEntity.status(400).body("Some error has occurred!");
  }
}
