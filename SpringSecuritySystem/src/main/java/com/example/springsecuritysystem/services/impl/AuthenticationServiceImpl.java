package com.example.springsecuritysystem.services.impl;

import com.example.springsecuritysystem.dao.UserDao;
import com.example.springsecuritysystem.dtos.AuthenticationRequest;
import com.example.springsecuritysystem.security.JwtService;
import com.example.springsecuritysystem.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link AuthenticationService} interface, responsible for handling
 * user authentication and JWT token generation.
 */
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserDao userDao;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  /**
   * Authenticates the user and generates a JWT token.
   *
   * @param request The authentication request containing email and password.
   * @return A JWT token if authentication is successful, otherwise null.
   */
  @Override
  public String authenticateAndGenerateToken(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    UserDetails user = userDao.findUserByEmail(request.email());
    if (user != null) {
      return jwtService.generateToken(user);
    }
    return null;
  }
}
