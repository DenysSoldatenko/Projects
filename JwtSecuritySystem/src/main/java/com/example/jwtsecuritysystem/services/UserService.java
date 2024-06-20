package com.example.jwtsecuritysystem.services;

import com.example.jwtsecuritysystem.dto.AdminDto;
import com.example.jwtsecuritysystem.dto.UserDto;
import com.example.jwtsecuritysystem.models.User;
import java.util.List;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

  UserDto createUser(User user);

  User getByUsername(String username);

  UserDto getById(Long id);

  AdminDto getAdminById(Long id);

  void removeUser(Long id);

  AdminDto createAdmin(User user);
}