package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.Status;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.repositories.RoleRepository;
import com.example.jwtsecuritysystem.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing User entities.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public User register(User user) {
    Role roleUser = roleRepository.findByName("ROLE_USER");
    List<Role> userRoles = new ArrayList<>();
    userRoles.add(roleUser);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(userRoles);
    user.setStatus(Status.ACTIVE);

    User registeredUser = userRepository.save(user);
    log.info("IN register - user: {} successfully registered", registeredUser);
    return registeredUser;
  }

  @Override
  public List<User> getAll() {
    List<User> result = userRepository.findAll();
    log.info("IN getAll - {} users found", result.size());
    return result;
  }

  @Override
  public User findByUsername(String username) {
    User result = userRepository.findByUsername(username);
    log.info("IN findByUsername - user: {} found by username: {}", result, username);
    return result;
  }

  @Override
  public User findById(Long id) {
    User result = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

    if (result == null) {
      log.warn("IN findById - no user found by id: {}", id);
      return null;
    }

    log.info("IN findById - user: {} found by id: {}", result, id);
    return result;
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
    log.info("IN delete - user with id: {} successfully deleted", id);
  }
}