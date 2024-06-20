package com.example.jwtsecuritysystem.security;

import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service for loading user details from the database for JWT authentication.
 */
@Slf4j
@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getByUsername(username);
    JwtUser jwtUser = JwtUserFactory.create(user);
    log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
    return jwtUser;
  }
}