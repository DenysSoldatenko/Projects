package com.example.springsecuritysystem.repositories;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link UserService} interface for managing application users.
 * Provides a hardcoded list of users for demonstration purposes.
 */
@Repository
public class UserServiceImpl implements UserService {

  private static final List<UserDetails> APPLICATION_USERS = asList(
    new User(
      "john.doe@gmail.com",
      "$2a$12$5eQY3spQwagdeevqjouf7.5em7Vx4Hvp.oJ4Na0Jc.nhjlRZhJTJ2",
      singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
    ),

    new User(
      "jane.doe@gmail.com",
      "$2a$12$5eQY3spQwagdeevqjouf7.5em7Vx4Hvp.oJ4Na0Jc.nhjlRZhJTJ2",
      singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    )
  );

  @Override
  public UserDetails findUserByEmail(String email) {
    return APPLICATION_USERS.stream()
      .filter(u -> u.getUsername().equals(email))
      .findFirst()
      .orElseThrow(() -> new UsernameNotFoundException("No user was found!"));
  }
}
