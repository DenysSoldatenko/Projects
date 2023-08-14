package com.example.springsecuritysystem.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing application users.
 */
@Repository
public class UserDao {
  private static final List<UserDetails> APPLICATION_USERS = Arrays.asList(
    new User(
      "john.doe@gmail.com",
      "$2a$12$5eQY3spQwagdeevqjouf7.5em7Vx4Hvp.oJ4Na0Jc.nhjlRZhJTJ2",
      Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
    ),

    new User(
      "jane.doe@gmail.com",
      "$2a$12$5eQY3spQwagdeevqjouf7.5em7Vx4Hvp.oJ4Na0Jc.nhjlRZhJTJ2",
      Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    )
  );

  /**
   * Find a user by their email address.
   *
   * @param email The email address of the user to find.
   * @return The UserDetails of the found user.
   * @throws UsernameNotFoundException If no user is found with the given email address.
   */
  public UserDetails findUserByEmail(String email) {
    return APPLICATION_USERS.stream()
    .filter(u -> u.getUsername().equals(email))
    .findFirst()
    .orElseThrow(() -> new UsernameNotFoundException("No user was found!"));
  }
}
