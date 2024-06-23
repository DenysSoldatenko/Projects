package com.example.jwtsecuritysystem.services.impl;

import static com.example.jwtsecuritysystem.models.Status.ACTIVE;
import static java.util.Collections.singletonList;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.Role;
import com.example.jwtsecuritysystem.models.User;
import com.example.jwtsecuritysystem.repositories.RoleRepository;
import com.example.jwtsecuritysystem.repositories.UserRepository;
import com.example.jwtsecuritysystem.services.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing User entities.
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  public final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDto createUser(User user) {
    return registerUserWithRole(user, "ROLE_USER", UserDto.class);
  }

  @Override
  public AdminDto createAdmin(User user) {
    return registerUserWithRole(user, "ROLE_ADMIN", AdminDto.class);
  }

  @Override
  public User getByUsername(String username) {
    User result = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
    log.info("IN findByUsername - user: {} found by username: {}", result, username);
    return result;
  }

  @Override
  public UserDto getById(Long id) {
    User result = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

    if (result == null) {
      log.warn("IN findById - no user found by id: {}", id);
      return null;
    }

    log.info("IN findById - user: {} found by id: {}", result, id);
    return modelMapper.map(result, UserDto.class);
  }

  @Override
  public AdminDto getAdminById(Long id) {
    User result = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    return modelMapper.map(result, AdminDto.class);
  }

  @Override
  public void removeUser(Long id) {
    userRepository.deleteById(id);
    log.info("IN delete - user with id: {} successfully deleted", id);
  }

  private <T> T registerUserWithRole(User user, String roleName, Class<T> dtoClass) {
    Role role = roleRepository.findByName(roleName);
    List<Role> userRoles = singletonList(role);

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(userRoles);
    user.setStatus(ACTIVE);

    User registeredUser = userRepository.save(user);
    log.info("IN registerUserWithRole - user: {} successfully registered with role: {}", registeredUser, roleName);

    return modelMapper.map(registeredUser, dtoClass);
  }
}